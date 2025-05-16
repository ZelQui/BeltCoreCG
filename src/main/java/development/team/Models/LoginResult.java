package development.team.Models;

public class LoginResult {
    private boolean success;
    private String icon;  // "error", "warning", "success", etc.
    private String title;
    private String message;

    // Constructor
    public LoginResult(boolean success, String icon, String title, String message) {
        this.success = success;
        this.icon = icon;
        this.title = title;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getIcon() { return icon; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
}

