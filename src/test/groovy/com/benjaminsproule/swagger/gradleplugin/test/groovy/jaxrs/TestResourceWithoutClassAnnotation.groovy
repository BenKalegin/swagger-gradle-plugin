package com.benjaminsproule.swagger.gradleplugin.test.groovy.jaxrs

import com.benjaminsproule.swagger.gradleplugin.ignore.IgnoredModel
import com.benjaminsproule.swagger.gradleplugin.test.model.RequestModel
import com.benjaminsproule.swagger.gradleplugin.test.model.ResponseModel
import com.benjaminsproule.swagger.gradleplugin.test.model.SubResponseModel
import io.swagger.annotations.*

import javax.ws.rs.*
import javax.ws.rs.core.Response

import static java.util.Collections.singletonList

@Api(tags = 'Test', description = 'Test resource', authorizations = @Authorization('basic'))
class TestResourceWithoutClassAnnotation {

    @ApiOperation('A basic operation')
    @Path('/root/withoutannotation/basic')
    @GET
    String basic() {
        return ''
    }

    @ApiOperation(value = 'A default operation')
    @Path('/root/withoutannotation/default')
    @GET
    Response defaultResponse() {
        return Response.ok().build()
    }

    @ApiOperation(value = 'A generics operation')
    @Path('/root/withoutannotation/generics')
    @POST
    List<String> generics(@ApiParam List<RequestModel> body) {
        return singletonList('')
    }

    @ApiOperation('Consumes and Produces operation')
    @Path('/root/withoutannotation/datatype')
    @Consumes('application/json')
    @Produces('application/json')
    @POST
    Response dataType(@ApiParam RequestModel requestModel) {
        return Response.ok().build()
    }

    @ApiOperation(value = 'A response operation', response = ResponseModel.class)
    @Path('/root/withoutannotation/response')
    @POST
    ResponseModel response(@ApiParam List<RequestModel> body) {
        return new ResponseModel()
    }

    @ApiOperation(value = 'A response container operation', response = ResponseModel.class, responseContainer = 'List')
    @Path('/root/withoutannotation/responseContainer')
    @POST
    List<ResponseModel> responseContainer(@ApiParam List<RequestModel> body) {
        return singletonList(new ResponseModel())
    }

    @ApiOperation('An extended operation')
    @Path('/root/withoutannotation/extended')
    @GET
    SubResponseModel extended() {
        return new SubResponseModel()
    }

    @ApiOperation('A deprecated operation')
    @Path('/root/withoutannotation/deprecated')
    @GET
    @Deprecated
    String deprecated() {
        return ''
    }

    @ApiOperation(value = 'An auth operation', authorizations =
        @Authorization(value = 'oauth2', scopes =
            @AuthorizationScope(scope = 'scope', description = 'scope description')
        )
    )
    @Path('/root/withoutannotation/auth')
    @GET
    String withAuth() {
        return ''
    }

    @ApiOperation(value = 'A model operation')
    @Path('/root/withoutannotation/model')
    @GET
    String model() {
        return ''
    }

    @ApiOperation('An overriden operation')
    @Path('/root/withoutannotation/overriden')
    @GET
    String overriden() {
        return ''
    }

    @ApiOperation('An overriden operation')
    @Path('/root/withoutannotaiton/overridenWithoutDescription')
    @GET
    String overridenWithoutDescription() {
        return ''
    }

    @ApiOperation(value = 'A hidden operation', hidden = true)
    @Path('/root/withoutannotation/hidden')
    @GET
    String hidden() {
        return ''
    }

    @ApiOperation(value = 'An ignored model')
    @Path('/root/withoutannotation/ignoredModel')
    @GET
    String ignoredModel(IgnoredModel ignoredModel) {
        return ''
    }
}
