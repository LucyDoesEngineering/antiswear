import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.regex.Pattern;

public class MessageFilter {

    private ImmutableList<String> swears;

    public MessageFilter(
            ImmutableList<String> swears
    ){
        this.swears = Preconditions.checkNotNull(swears);
    }

    private String getStarsForLength(int length){
        return new String(new char[length]).replace("\0", "★");
    }

    String filterMessage(String message){
        for (String swear : swears){
            message = message.replaceAll(
                    "(?i)" + Pattern.quote(swear), getStarsForLength(swear.length())
            );
        }
        return message;
    }

}
