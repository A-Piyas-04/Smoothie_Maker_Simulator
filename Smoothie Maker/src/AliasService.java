public class AliasService {
    public static String getAlias(int score) {
        if (score < -15) {
            return "Health Noob";
        } else if (score < -8) {
            return "Wellness Trainee";
        } else if (score < 0) {
            return "Fitness Explorer";
        } else if (score == 0) {
            return "Health Enthusist";
        } else if (score < 10) {
            return "Wellness Seeker";
        } else if (score < 20) {
            return "Fitness Advocate";
        } else {
            return "Health Guru";
        }
    }
}