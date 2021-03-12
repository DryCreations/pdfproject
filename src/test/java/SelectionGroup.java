import java.util.Arrays;
import java.util.List;

public class SelectionGroup {

    private Selection[] _selections;

    @Override
    public boolean equals(Object o) {

        if ( !(o instanceof  SelectionGroup) )
            return false;

        SelectionGroup other = (SelectionGroup)  o;

        return Arrays.equals(this._selections, other._selections);
    }
}
