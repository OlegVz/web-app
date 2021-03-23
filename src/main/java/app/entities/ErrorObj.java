package app.entities;

public class ErrorObj {
    private boolean isConSQLError = false;

    String errorMessage;

    String dbConnectionErrorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage() {
        this.errorMessage = "Fill in each of the following fields correct!";
    }

    public String getDbConnectionErrorMessage() {
        return dbConnectionErrorMessage;
    }

    public void setDbConnectionErrorMessage() {
        this.dbConnectionErrorMessage = "There is problem with connection to DB! You must add DB - e-store! ";
    }


    public boolean isConSQLError() {
        return isConSQLError;
    }

    public void setConSQLError(boolean conSQLError) {
        isConSQLError = conSQLError;
    }
}
