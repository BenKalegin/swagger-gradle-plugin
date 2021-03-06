package com.benjaminsproule.swagger.gradleplugin.model

import com.benjaminsproule.swagger.gradleplugin.classpath.ClassFinder
import com.benjaminsproule.swagger.gradleplugin.classpath.ResourceFinder
import org.gradle.api.Project
import spock.lang.Specification

class SwaggerExtensionTest extends Specification {
    SwaggerExtension swaggerExtension

    def setup() {
        def mockProject = Mock(Project)
        def mockClassFinder = Mock(ClassFinder)
        def mockResourceFinder = Mock(ResourceFinder)
        swaggerExtension = new SwaggerExtension(mockProject, mockClassFinder, mockResourceFinder)
    }

    def 'Valid swagger extension validation returns no errors'() {
        setup:
        def mock = Mock(ApiSourceExtension)
        mock.isValid() >> []
        swaggerExtension.apiSourceExtensions.add(mock)

        when:
        def result = swaggerExtension.isValid()

        then:
        assert !result
    }

    def 'Swagger extension with missing api source should provide missing api source error'() {
        when:
        def result = swaggerExtension.isValid()

        then:
        assert result
        assert result.contains('You must specify at least one apiSource element')
    }

    def 'Swagger extension empty api source should provide missing api source error'() {
        setup:
        swaggerExtension.apiSourceExtensions = []

        when:
        def result = swaggerExtension.isValid()

        then:
        assert result
        assert result.contains('You must specify at least one apiSource element')
    }

    def 'Errors from nested objects should be returned'() {
        setup:
        def mock = Mock(ApiSourceExtension)
        mock.isValid() >> ['nested error']
        swaggerExtension.apiSourceExtensions.add(mock)

        when:
        def result = swaggerExtension.isValid()

        then:
        assert result
        assert result.contains('nested error')
    }
}
