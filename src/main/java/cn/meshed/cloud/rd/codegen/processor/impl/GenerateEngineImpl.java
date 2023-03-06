package cn.meshed.cloud.rd.codegen.processor.impl;

import cn.hutool.json.JSONUtil;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.factory.TemplateFactory;
import cn.meshed.cloud.rd.codegen.model.JavaDefinition;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import com.alibaba.cola.exception.SysException;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Map;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class GenerateEngineImpl implements GenerateEngine {

    private final TemplateFactory factory;

    public GenerateEngineImpl() {
        this.factory = new TemplateFactory();
    }

    /**
     * 生成代码
     *
     * @param templateName 模板名称
     * @param data     数据
     * @return 生成的代码
     */
    @Override
    public String generate(String templateName, JavaDefinition data) throws SysException {
        Map dataMap = JSONUtil.toBean(JSONUtil.toJsonStr(data), Map.class);
        try {
            Template template = factory.getTemplate(String.format("%s.ftl",templateName));
            StringWriter sw = new StringWriter();
            template.process(dataMap,sw);
            return sw.toString();
        } catch (Exception e) {
            throw new SysException("生成代码异常，请检查参数是否丢失",e);
        }
    }
}
