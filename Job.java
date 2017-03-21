public class Job
{
    private boolean requiresCollege;
    private int salary;
    private String title;
    
    public Job(boolean requiresCollege, int salary, String title)
    {
        this.requiresCollege = requiresCollege;
        this.salary = salary;
        this.title = title; 
    }
    public int getSalary()
    {
        return salary;
    }
    public String getTitle()
    {
        return title;
    }
    public boolean getRequiresCollege()
    {
        return requiresCollege;
    }
}
