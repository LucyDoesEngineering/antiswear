import com.google.common.base.Preconditions;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{

    private String domChannel;
    private String subId;
    private MessageFilter messageFilter;

    public MessageListener(
            String domChannel,
            String subId,
            MessageFilter messageFilter
    ){
        this.domChannel = Preconditions.checkNotNull(domChannel);
        this.subId = Preconditions.checkNotNull(subId);
        this.messageFilter = Preconditions.checkNotNull(messageFilter);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().getId().equals(subId)){
            if (event.getChannel().getId().equals(domChannel)){
                // Can swear because swear reports go here.
            }
            else{
                String messageContent = event.getMessage().getContentRaw();
                String messageAfterFilter = messageFilter.filterMessage(messageContent);
                if (!messageContent.equals(messageAfterFilter)){
                    event.getMessage().editMessage(messageAfterFilter).complete();
                    //event.getJDA().getPrivateChannelById(domChannel)
                    //        .sendMessage("Lucy swore in message: " + messageContent).complete();
                }
            }
        }
    }
}
