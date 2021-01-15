public class Button {
    private OnClickListener listener;
    public boolean canClick = false;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
        this.canClick = true;
    }

    public void dispatchTouchEvent(int type) {
        listener.onClick(type);
    }
}
