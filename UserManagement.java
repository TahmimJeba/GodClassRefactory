public class UserManagement {
    private static final String TABLE_USER = "user";
    private static final String TABLE_TEACHING = "teaching";
    private static final String TABLE_ROLE = "role";
    
    /* Inserting user to database */
    public void InsertUser (User pUser){
        boolean exist = ExistUser(pUser);
        String sql = "INSERT INTO "+ UserManagement.TABLE_USER +"...";
        //query
    }
    
    /* Update existing user */
    public void UpdateUser (User pUser){
        boolean exist = ExistUser(pUser);
        String sql = "UPDATE "+ UserManagement.TABLE_USER+"...";
    }
    
    /* delete existing user from database */
    public void DeleteUser (User pUser){
        boolean exist = ExistUser(pUser);
        String sql = "DELETE FROM "+ UserManagement.TABLE_USER+"...";
        //permanent delete
    }
    
    /* check user exists or not */
    public void ExistUser (User pUser){
        String sql = "SELECT * FROM "+ UserManagement.TABLE_USER+"...";
        //check
    }
    
    /* check mendatory field */
    public boolean checkMandatoryFieldUser(User pUser){
        return pUser.getMandatory();
    }
    
    /* Insert teaching */
    public void InsertTeaching (Teaching pTeaching){
        String sql = "INSERT INTO "+UserManagement.TABLE_TEACHING+"...";
    }
    
    /* update teaching */
    public void UpdateTeaching (Teaching pTeaching){
        String sql = "UPDATE "+ UserManagement.TABLE_TEACHING+"...";
    }
    
    /* Delete teaching */
    public void DeleteTeaching (Teaching pTeaching){
        String sql = "DELETE FROM "+UserManagement.TABLE_TEACHING+"...";
    }

    /* check mendatory field */
    public boolean checkMandatoryFieldTeaching(Teaching pTeaching){
        return pTeaching.getMandatory();
    }
    
    /* Insert role to database */
    public void InsertRole (Role pRole){
        String sql = "INSERT INTO "+ UserManagement.TABLE_ROLE+"...";
    }
    
    /* delete role */
    public void DeleteRole (Role pRole){
        String sql = "DELETE FROM "+ UserManagement.TABLE_ROLE+"...";
    }
    
    /* check mendatory field */
    public boolean checkMandatoryFieldRole(Role pRole){
        return pRole.getMandatory();
    }
}