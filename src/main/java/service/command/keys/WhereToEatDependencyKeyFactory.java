package service.command.keys;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class WhereToEatDependencyKeyFactory implements TenacityPropertyKeyFactory {
 
	@Override
    public TenacityPropertyKey from(String value) {
        return WhereToEatDependencyKeys.valueOf(value.toUpperCase());
    }
}