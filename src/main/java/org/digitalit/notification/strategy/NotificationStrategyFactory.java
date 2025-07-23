package org.digitalit.notification.strategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.springframework.stereotype.Component;

@Component
public class NotificationStrategyFactory {

  private final Map<NotificationChannel, NotificationStrategy> strategies =
      new EnumMap<>(NotificationChannel.class);

  public NotificationStrategyFactory(List<NotificationStrategy> strategyList) {
    for (NotificationStrategy strategy : strategyList) {
      strategies.put(strategy.getChannel(), strategy);
    }
  }

  public NotificationStrategy getStrategy(NotificationChannel channel) {
    return strategies.get(channel);
  }
}
