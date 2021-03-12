import com.sun.javafx.stage.StageHelper;

public class Selection {

    private String _text;

    private long _position;

    public Selection() {
        _text = "";
        _position = -1;
    }

    public Selection(String text, int position) {
        _text = text;
        _position = position;
    }

    public String getText() {
        return _text;
    }

    public long getPosition() {
        return _position;
    }

    @Override
    public boolean equals(Object o) {

        if ( !(o instanceof Selection) )
            return false;

        Selection other = (Selection) o;

        return this._position == other._position
                && this._text.equals(other._text);
    }
}
