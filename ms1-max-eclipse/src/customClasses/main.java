
package customClasses;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main {
    public static void main( String[] args ) {
        Scanner sc = new Scanner( System.in );
        // test Date 
        Date today = getToday();
        System.out.println("Today is");
        today.printDate();
        HashSet<String> hSet = new HashSet<String>();
        Vector<Project> oVector = gettigStarted( sc, hSet); //thanks to Ahmet Altundag for explaining me the need for the second wildcard. After NodeJS I kinda forgot about this thing 
        hSet.add( oVector.elementAt(0).getPName() );
        //oVector.elementAt(0).printProject();
        /**This is the menu routine */
        while(true){
            menutext();
            switch ( Integer.parseInt(sc.nextLine()) ) {
                case 1:
                    oVector.add ( createProject( sc, oVector, hSet ) );
                    hSet.add( oVector.elementAt( oVector.size()-1 ).getPName());
                    if( oVector.size() == 0 ) System.out.println("no projects");
                break;

                case 2:
                System.out.println("---------------------------------------------------------------");
                System.out.println( "please enter the PID number" );
                System.out.println("---------------------------------------------------------------");
                oVector.remove( sc.nextInt() );
                System.out.println("Project has been removed");
                System.out.println("---------------------------------------------------------------");
                break;

                case 3:
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All listed projects" );
                    System.out.println( "---------------------------------------------------------------" );
                    for ( int i = 0; i < oVector.size(); i++ ){
                        oVector.elementAt(i).printProject();
                        //the reminace of a quick and dirty solution
                        //pro = (Project) oVector.elementAt(i); //Thanks Kevin Kopp for helping out and telling me to cast because I didn't need to in the vector sample+
                        //pro.printProject();
                    }
                    System.out.println( "---------------------------------------------------------------" );
                    break;
                
                case 4:
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All current projects" );
                    System.out.println( "---------------------------------------------------------------" );
                    boolean b = false;
                    for ( int i = 0; i < oVector.size(); i++ ) {
                        b = today.isEqualEarlierDate( oVector.elementAt(i).getPEndDate() ) && oVector.elementAt(i).getPStartDate().isEqualEarlierDate( today ) ;
                        if( b ) oVector.elementAt(i).printProject();
                    }
                    break;
            
                case 5: 
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All upcoming projects" );
                    System.out.println( "---------------------------------------------------------------" );
                    for ( int i = 0; i < oVector.size(); i++ ) {
                        if( oVector.elementAt(i).getPStartDate().isEarlierDate( today ) ) oVector.elementAt(i).printProject();
                    } 
                    break;
                
                case 6:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("All past projects");
                    System.out.println("---------------------------------------------------------------");
                    for (int i = 0; i < oVector.size(); i++) {
                        if ( today.isEqualEarlierDate(oVector.elementAt(i).getPEndDate() ) ) oVector.elementAt(i).printProject();
                    }
                    break;
                
                case 7:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("are you sure, all you Projects will be lost");
                    System.out.println("type \"confirm\" to close the program");
                    System.out.println("---------------------------------------------------------------");
                    String closeDiagString = sc.nextLine();
                    if( closeDiagString.equals("confirm") ) {
                        sc.close();
                        return;
                    } else break;
                    
                default:
                    System.out.println( "this was not a valid selection" );
                    break;
            }
        }
    }

    public static void menutext(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("now choose between");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1 | create new Project ");
        System.out.println("2 | delete Prject by id");
        System.out.println("3 | list all projects");
        System.out.println("4 | list current projects");
        System.out.println("5 | list upcoming projects");
        System.out.println("6 | list past prjects");
        System.out.println("7 | end the programm");
        System.out.println("---------------------------------------------------------------");
    }

    public static Vector<Project> gettigStarted(Scanner sc, HashSet<String> hSet) {
        Vector<Project> oVector = new Vector<Project>();
        System.out.println("Let's start you with a new Project here");
        oVector.add(createProject(sc, oVector, hSet));
        return oVector;
    }

    public static Project createProject(Scanner sc, Vector<Project> oVector, HashSet<String> hSet) {
        String pname = getPNameString(oVector, sc, hSet);
        Date pEndDate = new Date();
        Date pStartDate = new Date();
        //please refactor later this is not optimal, there should be a recoursive solution
        try {
            String start = "start";
            String end = "end";
            pStartDate = scanDate(sc, start);
            pEndDate = scanDate(sc, end);
            while( pStartDate.isEarlierDate(pEndDate)){
                System.out.println("---------------------------------------------------------------");
                System.out.println("there might have been an error let's check that again");
                System.out.println("---------------------------------------------------------------");
                pStartDate = scanDate(sc, start);
                pEndDate = scanDate(sc, end);
            }
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("----------------------");
            System.err.println("|inproper date format|");
            System.out.println("----------------------");
            System.out.println("| restarting creator |");
            System.out.println("----------------------");
            return createProject(sc, oVector, hSet);
        }

        Project createProject = new Project(oVector.size(), pname, pStartDate, pEndDate);
        System.out.println("---------------------------------------------------------------");
        System.out.println("you created project is");
        createProject.printProject();
        System.out.println("---------------------------------------------------------------");

        return createProject;
    }

    public static Date scanDate( Scanner sc, String dateType ) throws ArrayIndexOutOfBoundsException {
        System.out.println("so whats the " + dateType + " date");
        System.out.println("please enter date like (YYYY/MM/DD)");
        String[] inDate = sc.nextLine().split("/");
        // litte date validation, not in depth but the biggest mistakes will be stoped
        Date scDate = new Date(Integer.parseInt(inDate[0]), Integer.parseInt(inDate[1]), Integer.parseInt(inDate[2]) );
        if( Integer.parseInt(inDate[1]) > 12 || Integer.parseInt(inDate[2]) > 31) scDate = scanDate(sc, dateType);
        return scDate;
    }

    public static String getPNameString(Vector<Project> oVector, Scanner sc, HashSet<String> hSet){
        System.out.println("please enter a project name");
        String pname = sc.nextLine();
        //empty name avoidance - found it here: https://www.tutorialspoint.com/check-if-a-string-is-empty-or-null-in-java
        //also edited to cap singel space hit, I kinda think this is enough
        if( pname == null || pname.length() == 0 || pname.equals(" ")) return getPNameString(oVector, sc, hSet);
        //test if name is taken
        //could be redone with sets but this works for now
        for (int i = 0; i < oVector.size(); i++) {
            if ( hSet.contains(pname) ) {
                System.out.println("The name has allready been taken by Project id: " + oVector.elementAt(i).getId());
                return getPNameString(oVector, sc, hSet);
            }
        } 
        return pname;
    }

    public static Date getToday() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("|This Program uses LocalDate so the date might be one day off.|");
        System.out.println("---------------------------------------------------------------");
        
        //credits https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate)); //YYYY/MM/DD
        
        String[] todayString = dtf.format(localDate).split("/");
        Date todayDate = new Date(Integer.parseInt(todayString[0]), Integer.parseInt(todayString[1]), Integer.parseInt(todayString[2]) );
        //todayDate.printDate();
        return todayDate;
    }
}
