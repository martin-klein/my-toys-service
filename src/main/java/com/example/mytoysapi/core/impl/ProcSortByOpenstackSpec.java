package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.core.model.NavigationLink;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Generic sorting function according to the Openstack specification
 * (https://specs.openstack.org/openstack/api-wg/guidelines/pagination_filter_sort.html#sorting). Sorts by fields on the sortable object being passed.
 * Could be moved to a common project.
 */
@Component
public class ProcSortByOpenstackSpec<T> {

    public List<T> sort(@NotNull List<T> sortableObject, @NotNull LinkedHashMap<String, OrderTypeEnum> sortSpec) {
        ComparatorChain<Object> chain = new ComparatorChain<>();
        for(Map.Entry<String, OrderTypeEnum> entry : sortSpec.entrySet()) {
            Comparator<Object> entryComparator = new BeanComparator<>(entry.getKey());
            if(entry.getValue()==OrderTypeEnum.DESC) {
                entryComparator = entryComparator.reversed();
            }
            chain.addComparator(entryComparator);
        }

        sortableObject.sort(chain);
        return sortableObject;
    }
}
