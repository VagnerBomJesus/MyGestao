package stp.vagnerjesus.mygestao;

public class ScreenItem {

        String Title;
    String Description;

    public String getTitle1() {
        return Title1;
    }

    public void setTitle1(String title1) {
        Title1 = title1;
    }

    String Title1;
        int ScreenImg;

        public ScreenItem(String title, String title1, String description, int screenImg){
            Title = title;
            Title1 = title1;
            Description =description;
            ScreenImg = screenImg;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public int getScreenImg() {
            return ScreenImg;
        }

        public void setScreenImg(int screenImg) {
            ScreenImg = screenImg;
        }
    }