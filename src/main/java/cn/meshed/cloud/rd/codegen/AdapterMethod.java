package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.constant.RequestType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_FORMAT;
import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_SIMPLE_PARAMETER_FORMAT;

/**
 * <h1>适配器方法</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AdapterMethod extends Method {

    private String uri;

    private RequestType requestType;

    /**
     * 注解
     */
    @Override
    public Set<String> getAnnotations() {
        if (this.requestType != null) {
            String requestMapping = this.requestType.getMapping();
            if (StringUtils.isNotBlank(this.uri)) {
                super.addAnnotation(String.format(ANNOTATION_SIMPLE_PARAMETER_FORMAT, requestMapping, this.uri));
            } else {
                super.addAnnotation(String.format(ANNOTATION_FORMAT, requestMapping));
            }
        }
        return super.getAnnotations();
    }
}