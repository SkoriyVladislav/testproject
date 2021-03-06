package com.aem.exadel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import java.util.List;


@Model(adaptables = Resource.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ManualCard implements Card{
    private CardType cardType = CardType.MANUAL;
    private List<News> news;
}
