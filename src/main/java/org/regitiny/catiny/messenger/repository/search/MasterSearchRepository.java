package org.regitiny.catiny.messenger.repository.search;

import org.regitiny.catiny.messenger.domain.Master;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Master} entity.
 */
public interface MasterSearchRepository extends ElasticsearchRepository<Master, Long> {
}
