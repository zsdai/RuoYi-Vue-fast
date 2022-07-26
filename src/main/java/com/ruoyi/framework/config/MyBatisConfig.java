package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ruoyi.common.utils.StringUtils;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Mybatis支持*匹配扫描包
 *
 * @author ruoyi
 */
@Configuration
public class MyBatisConfig
{
    @Autowired
    private Environment env;

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public static String setTypeAliasesPackage(String typeAliasesPackage)
    {
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<String> allResult = new ArrayList<String>();
        try
        {
            for (String aliasesPackage : typeAliasesPackage.split(","))
            {
                List<String> result = new ArrayList<String>();
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = resolver.getResources(aliasesPackage);
                if (resources != null && resources.length > 0)
                {
                    MetadataReader metadataReader = null;
                    for (Resource resource : resources)
                    {
                        if (resource.isReadable())
                        {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try
                            {
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            }
                            catch (ClassNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0)
                {
                    HashSet<String> hashResult = new HashSet<String>(result);
                    allResult.addAll(hashResult);
                }
            }
            if (allResult.size() > 0)
            {
                typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
            }
            else
            {
                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }

    public Resource[] resolveMapperLocations(String[] mapperLocations)
    {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        if (mapperLocations != null)
        {
            for (String mapperLocation : mapperLocations)
            {
                try
                {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    /**
     * 这里在applications.properties里面配置了
     * mybatis-plus.type-aliases-package=com.jwt.springboot.dao
     * mybatis-plus.mapper-locations=classpath:mapper/*Mapper.xml
     * 但多数据源情况下执行sql总会报：org.apache.ibatis.binding.BindingException:
     * Invalid bound statement (not found)........
     * 原因是 this.mapperLocations 为null
     *
     * 注！！！！这里有大坑， 因为这里是自定义的sqlSessionFactoryBean，所以导致
     * 没有启动时没有通过org.mybatis.spring.boot.autoconfigure.MybatisPlusAutoConfiguration
     * 类的sqlSessionFactory(DataSource dataSource)方法自动装配sqlSessionFactoryBean
     * 自定义的sqlSessionFactoryBean所以也没设置mapperLocations
     * 故自定义实例化sqlSessionFactoryBean这里需要手动设置mapperLocations
     * 可参考：https://developer.aliyun.com/article/754124
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception
    {
        String typeAliasesPackage = env.getProperty("mybatis-plus.type-aliases-package");
        String mapperLocations = env.getProperty("mybatis-plus.mapper-locations");
        //String configLocation = env.getProperty("mybatis-plus.config-location");
        typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
        VFS.addImplClass(SpringBootVFS.class);

        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setMapperLocations(resolveMapperLocations(StringUtils.split(mapperLocations, ",")));
        //sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        return sessionFactory.getObject();
    }

}
