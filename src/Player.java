import java.awt.*;

public class Player {
    public String role;
    Color color;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    //int units number
    Player(String role,Color color){
        try {
            this.role = role;
            this.color = color;
            if (role.equals("bot")) {
                Bot bot = new Bot(color);
                bot.start();
            } else {

            }
        }catch (Exception ex){

        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this.color.equals(((Player) obj).color)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception ex){
            return false;
        }
    }
}
