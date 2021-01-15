public class Activity implements OnClickListener {
    private Button btn;
    public Activity() {
        init();
    }

    private void init() {
        btn = new Button();
        btn.setOnClickListener(this);
    }


    public void dispatchTouchEvent(int type) { // activity dispatch click_event to btn which can click
        if (btn.canClick && type < 10) {
            btn.dispatchTouchEvent(type);
        }
    }


    @Override
    public void onClick(int type) {
        System.out.println("btn get the click_event type: " + type);
    }

    public static void main(String[] args) {
        Activity activity = new Activity();
        activity.dispatchTouchEvent(6);
    }
}
