/*
 * Copyright (c) 2019 A
 * ServiceCatalog API
 * # About the ONAP Service Catalog API  The Service Catalog API is based on a subset of the TM Forum 633 Service Catalog Management API. ## API Overview ### General Description  This Service Catalog API is used to retrieve as well as create the Service Specifications that ONAP supports. As Services are designed and distributed in ONAP SDC, the Service Catalog API allows external systems such as the Business Support Systems (BSS) to discover and create these Services  The Service Design Center (SDC) of ONAP allows for the design of Services that can be orchestrated by ONAP. ONAP SDC provides the tools to design the Services composition and topology, which is then represented in TOSCA. This Service Catalog API allows a REST API JSON representation of the Service, including the required attributes needed for Service Instantiation of this Service Topology. ### Relationship and Dependencies  This Service Catalog API is related to the TM Forum 633 Service Catalog Management API and also to MEF LSO LEGATO Service Catalog API.  This API takes a subset of the Service Catalog Management API, namely the ServiceSpecification resource, and maps this standard resource to the ONAP SDC Service Templates/Topology. By doing this we allow a well known, standardized JSON REST representation of the Service Templates available in ONAP to be potentially ordered via Service Orders.   ### API Structure and Approach The Service Catalog API can be used to get the details of a specific ONAP Service Template, or can be used to search/list the Service Templates that are available.  ## Getting Started with the API (Hello World) The Service Catalog API is available via two schemes, http and https. For interacting with the API the simplest method is via http. To interact with the this API via https, your Client would be required to load the neccessary https certificate.   The default installation of ONAP via OOM, will configure the use of two Node ports for External APIs. NodePorts are used to allow client applications, that run outside of Kubernetes, access to ONAP components deployed by OOM. For this Service Catalog API, the External API Framework has a http Node port of 30274. For https, the Node port is 30292.   From outside ONAP, a client can interact with External API by first getting the IP that the NBI docker container is running on, and then using the appropriate nodeport according to the access scheme. For example to access this Service Catalog API to retrieve a list of all Service Specifications available in ONAP you can use http method GET to http://{nbi_ip}:30274/nbi/api/v4/serviceSpecification/  ### SDK quick intro There are many tools that can be used to view and edit this swagger file like swagger editor, Atom and senya. For example this swagger file can be loaded into https://editor.swagger.io/. This UI acts both as an online editor and viewer.  ### How to start the client side implementation * Code generation, is available via the Generate Client option in the swagger editor. Client stubs can be generated in multiple languages, for example java, go, python etc. These Client stub code can be incorporated in the Application you wish to access the Service Catalog API from.  ### How to start the server side implementation * Not applicable, the service side for this API will be the NBI container running the External API Framework Springboot application.  ## API Description Includes summary of information drawn from API definitions in OpenAPI / Swagger files ### Resource Endpoint / Resource Quick Reference GET /serviceSpecification/ : This operation returns a list service specifications from a catalog  POST /serviceSpecification/ : This operation creates a service specification in catalog  GET /serviceSpecification/{id} : This operation returns the service specifications from a catalog associated with this id. Note the id maps to the uuid of the SDC Service Template in the SDC catalog  GET /serviceSpecification/{id}/specificationInputSchema : This operation returns a service specification Input schema by its id from a catalog. Note again the id corresponds to the uuid of the Service Template in SDC.  ### Data Schema #### Main API Entities Describe the major entities used in the API  The main entity of the API is the ServiceSpecification resource. This entity is the top level entity of the API, and is returned as either a single instance when queried with id, or as JSON arroy of ServiceSpecification entities when queried as a list.  The major child enties are relatedParty which points to the designer of the Template in SDC. The resourceSpecification which point to child resources for the Service Template. The serviceSpecCharacteristics entities are used to describe the attributes that can be supplied to instantiate a Service Instance of this Service Template. #### Payload data structures If any, describe the appropriate data structures that are included within payload of the API.  Not applicable ### Security on the API Authentication; Authorization; Credentials/access token; etc.   https certificate required if using https. No authentication on http requests.In production this API should be behind an API Gateway with the necessary authentication ### Response Codes The meaning of Status Codes & Errors  See response codes for each API resource in the API section below ### Rate Limits and Thresholds Requests per unit time allowed; Pagination   No rate limits or thresholds, in production this API should be behind an API Gateway with the necessary limits. ### Validation constraints Describe any behavioral and structural validation constraints  Not applicable ### Assumptions For example, any Pre/Post conditions   For this API to function and return Service Specifications, SDC is required to be running and Service models designed in the SDC catalog ## API Interactions and Flows ### Interaction Examples Illustrate sequence of client calls to this API, possibly based on Use Cases, presented with diagrams, tables, etc  The Service Catalog API flow of use can generally follow the sequence below   #### Call ONAP to discover what available services it can offer  ``` curl -X GET \"http://serverRoot:30274/nbi/api/v4/serviceSpecification/\" -H \"accept: application/json;charset=utf-8\" ``` #### Example Response Values ``` [   {          \"id\": \"0ec83a1f-51e7-44e7-b773-3f37ddb937cd\",          \"name\": \"EPLServiceTemplate\",          \"invariantUUID\": \"ddf31f35-8e71-4f5a-a383-4241b87ca7a7\",          \"category\": \"Network L4+\",          \"distributionStatus\": \"DISTRIBUTED\",          \"version\": \"1.0\",          \"lifecycleStatus\": \"CERTIFIED\",          \"relatedParty\": {              \"id\": \"jm0007\",              \"role\": \"lastUpdater\"          }      } ] ``` #### Using the id returned from the list of Service Specifications, drill into any specific Service Specificaton you want to orchestrate/order using  ``` curl -X GET \"http://serverRoot:30274/nbi/api/v4/serviceSpecification/0ec83a1f-51e7-44e7-b773-3f37ddb937cd\" -H \"accept: application/json;charset=utf-8\" ``` #### Example Response Values ``` {      \"id\": \"0ec83a1f-51e7-44e7-b773-3f37ddb937cd\",      \"name\": \"EPLServiceTemplate\",      \"invariantUUID\": \"ddf31f35-8e71-4f5a-a383-4241b87ca7a7\",      \"toscaModelURL\": \"/sdc/v1/catalog/services/0ec83a1f-51e7-44e7-b773-3f37ddb937cd/toscaModel\",      \"category\": \"Network L4+\",      \"distributionStatus\": \"DISTRIBUTED\",      \"version\": \"1.0\",      \"lifecycleStatus\": \"CERTIFIED\",      \"relatedParty\": {          \"id\": \"jm0007\",          \"name\": \"Joni Mitchell\",          \"role\": \"lastUpdater\"      },      \"resourceSpecification\": [          {              \"id\": \"ec910118-ba94-4517-98b5-5bc10f277f4a\",              \"version\": \"1.0\",              \"name\": \"TestVF_1579291137027\",              \"resourceInstanceName\": \"TestVF_1579291137027 0\",              \"modelCustomizationName\": \"TestVF_1579291137027 0\",              \"resourceInvariantUUID\": \"df329320-fe21-49c2-96a8-7217ac7143de\",              \"resourceType\": \"VF\",              \"@type\": \"ONAPresource\",              \"modelCustomizationId\": \"ad5fb501-e472-4d79-a303-1a4a56c0fa75\"          }      ],      \"href\": \"serviceSpecification/0ec83a1f-51e7-44e7-b773-3f37ddb937cd\",      \"attachment\": [],      \"@type\": \"ONAPservice\",      \"instantiationType\": \"A-la-carte\",      \"serviceSpecCharacteristic\": {          \"name\": \"TestService_1579291137027_ServiceCharacteristics\",          \"description\": \"This object describes all the inputs needed from the client to interact with the TestService_1579291137027 Service Topology\",          \"valueType\": \"object\",          \"@type\": \"ONAPServiceCharacteristic\",          \"@schemaLocation\": \"null\",          \"serviceSpecCharacteristicValue\": {              \"valueType\": \"object\",              \"@schemaLocation\": \"/serviceSpecification/0ec83a1f-51e7-44e7-b773-3f37ddb937cd/specificationInputSchema\",              \"@type\": \"TestService_1579291137027_ServiceCharacteristic\"          }      }  } ```  #### To access Json Schema of the Parameters required to instantiate the Service from this Service Specification  ``` curl -X GET \"http://serverRoot:30274/nbi/api/v4/serviceSpecification/0ec83a1f-51e7-44e7-b773-3f37ddb937cd/specificationInputSchema\" -H \"accept: application/json;charset=utf-8\" ``` #### Example Response Values ``` {    \"ServiceCharacteristics\" : {      \"required\" : [ \"mscmevcendpointa_evcendpoint_endPointId\", \"mscmevcendpointz_evcendpoint_endPointId\", \"mscmsubscriberunia_subscriberuni_uniIdentifier\", \"mscmsubscriberuniz_subscriberuni_uniIdentifier\" ],      \"properties\" : {        \"mscmevcendpointa_evcendpoint_map\" : {          \"type\" : \"string\",          \"description\" : \"\"        },        \"mscmsubscriberunia_subscriberuni_uniIdentifier\" : {          \"type\" : \"string\",          \"description\" : \"String that is used to allow the Sub-scriber and Service Provider to uniquely identify the UNI for oper-ations purposes.\"        },        \"mscmevc0_evc_evcType\" : {          \"type\" : \"string\",          \"description\" : \"Point-to-Point, Multipoint-to-Mul-tipoint, or Rooted-Multipoint.\"        },        \"mscmevcendpointz_evcendpoint_map\" : {          \"type\" : \"string\",          \"description\" : \"\"        },        \"mscmevcendpointz_evcendpoint_endPointUni\" : {          \"type\" : \"string\",          \"description\" : \"Specify the UNI where the EvcEndPoint is located. The EvcEndPoint is said to be at this Uni.\"        },        \"mscmevcendpointz_evcendpoint_ingressBwp\" : {          \"type\" : \"string\",          \"description\" : \"\"        },        \"mscmsubscriberuniz_subscriberuni_uniIdentifier\" : {          \"type\" : \"string\",          \"description\" : \"String that is used to allow the Sub-scriber and Service Provider to uniquely identify the UNI for oper-ations purposes.\"        },        \"mscmevcendpointz_evcendpoint_endPointId\" : {          \"type\" : \"string\",          \"description\" : \"A string that is used to allow the Subscriber and Service Provider to uniquely identify the EvcEndPoint for operations purposes.\"        },        \"mscmevcendpointa_evcendpoint_endPointId\" : {          \"type\" : \"string\",          \"description\" : \"A string that is used to allow the Subscriber and Service Provider to uniquely identify the EvcEndPoint for operations purposes.\"        },        \"mscmevcendpointa_evcendpoint_ingressBwp\" : {          \"type\" : \"string\",          \"description\" : \"\"        },  \"mscmevc0_evc_listOfEvcEps\" : {          \"type\" : \"array\",          \"description\" : \"A list of EVC EP ID Service Attribute values.\"        },        \"mscmevc0_evc_evcId\" : {          \"type\" : \"string\",          \"description\" : \"\"        },        \"mscmevcendpointa_evcendpoint_endPointUni\" : {          \"type\" : \"string\",          \"description\" : \"Specify the UNI where the EvcEndPoint is located. The EvcEndPoint is said to be at this Uni.\"        }      }    }   ```  ## Tutorials Reference any tutorials or use cases. May use links.  To learn how the  BBS use case used these APIs, please find the Low Level Designs at: https://wiki.onap.org/pages/viewpage.action?pageId=48532377  ## API Mapping Details Includes: * Mapping between use cases/requirements and API calls. The BBS Use case used the Service Catalog API to learn the details of the Broad Service. This ServiceSpecification was then included in a Product Ordering as a Service Candidate from the BSS system.  ## Glossary ### API Version  The version number has major, minor and revision numbers. E.g. v4.1.0 Only the major version number (without the minor number and revision number) is held in the URL. APIs are described with a major version with “v” following the API Name, e.g.: nbi/api/v4/serviceSpecification. The schema associated with a REST API must have its version number aligned with that of the REST API.  The major version number is incremented for an incompatible change. The minor version number is incremented for a compatible change. For minor modifications of the API, version numbering must not be updated, provided the following backward compatibility rules are respected: * New elements in a data type must be optional (minOccurs=0) * Changes in the cardinality of an attribute in a data type must be from mandatory to optional or from lower to greater * New attributes defined in an element must be optional (absence of use=”required”) * If new enumerated values are included, the former ones and its meaning must be kept * If new operations are added, the existing operations must be kept * New parameters added to existing operations must be optional and existing parameters must be kept  For major modifications of the API, not backward compatible and forcing client implementations to be changed, the major version number must be updated.
 *
 * OpenAPI spec version: 4.1.0
 * Contact: onap-discuss@lists.onap.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */


package org.onap.nbi.apis.servicecatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Party linked to the service catalog. in nbi we retrieve information about last updater of the service in SDC
 */
@ApiModel(description = "Party linked to the service catalog. in nbi we retrieve information about last updater of the service in SDC")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-08-07T15:33:56.969+05:30")



public class RelatedPartyRef {

  @NotEmpty(message = "is missing in the request!")
  private String id = null;

  private String role = null;

  private String name = null;

  public RelatedPartyRef id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier of the related party. Filled with lastUpdaterUserId
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier of the related party. Filled with lastUpdaterUserId")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RelatedPartyRef role(String role) {
    this.role = role;
    return this;
  }

   /**
   * Role payed by the related party Only role &#39;lastUpdater&#39; is retrieved in Beijing release
   * @return role
  **/
  @ApiModelProperty(value = "Role payed by the related party Only role 'lastUpdater' is retrieved in Beijing release")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public RelatedPartyRef name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the related party - Filled with lastUpdatedFullName
   * @return name
  **/
  @ApiModelProperty(value = "Name of the related party - Filled with lastUpdatedFullName")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedPartyRef relatedPartyRef = (RelatedPartyRef) o;
    return Objects.equals(this.id, relatedPartyRef.id) &&
        Objects.equals(this.role, relatedPartyRef.role) &&
        Objects.equals(this.name, relatedPartyRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedPartyRef {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

