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

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ServiceSpecification is a class that offers characteristics to describe a type of service. Functionally, it acts as a template by which Services may be instantiated. By sharing the same specification, these services would therefore share the same set of characteristics.
 */
@ApiModel(description = "ServiceSpecification is a class that offers characteristics to describe a type of service. Functionally, it acts as a template by which Services may be instantiated. By sharing the same specification, these services would therefore share the same set of characteristics.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-08-07T15:33:56.969+05:30")



public class ServiceSpecificationRequest {

  @NotEmpty(message = "is missing in the request!")
  private String name = null;

  @NotEmpty(message = "is missing in the request!")
  private String description = null;

  private String type = "ONAPservice";

  private String schemaLocation = null;

  private String baseType = null;

  private String toscaModelURL = null;

  private String toscaResourceName = null;

  @NotEmpty(message = "is missing in the request!")
  private String category = null;

  private String subcategory = null;

  private String version = null;

  private LifecycleStatusValues lifecycleStatus = null;

  private TargetServiceSchemaRef targetServiceSchema = null;

  private List<Attachment> attachment = null;

  @NotEmpty(message = "is missing in the request!")
  @Valid
  private List<RelatedPartyRef> relatedParty = null;

  private List<ResourceSpecificationRef> resourceSpecification = null;

  private List<ServiceSpecCharacteristicRequest> serviceSpecCharacteristic = null;

  public ServiceSpecificationRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the service specification
   * @return name
  **/
  @ApiModelProperty(value = "Name of the service specification")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceSpecificationRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * A narrative that explains in detail what the service specification
   * @return description
  **/
  @ApiModelProperty(value = "A narrative that explains in detail what the service specification")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceSpecificationRequest type(String type) {
    this.type = type;
    return this;
  }

   /**
   * This attribute allows to dynamically extends TMF class. Valued with &#39;ONAPservice&#39;. We use this feature to add following attributes: toscaModelURL toscaResourceName category (1) subcategory (1) distributionStatus
   * @return type
  **/
  @ApiModelProperty(value = "This attribute allows to dynamically extends TMF class. Valued with 'ONAPservice'. We use this feature to add following attributes: toscaModelURL toscaResourceName category (1) subcategory (1) distributionStatus")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ServiceSpecificationRequest schemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
    return this;
  }

   /**
   * Not used
   * @return schemaLocation
  **/
  @ApiModelProperty(value = "Not used")
  public String getSchemaLocation() {
    return schemaLocation;
  }

  public void setSchemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
  }

  public ServiceSpecificationRequest baseType(String baseType) {
    this.baseType = baseType;
    return this;
  }

   /**
   * Not used
   * @return baseType
  **/
  @ApiModelProperty(value = "Not used")
  public String getBaseType() {
    return baseType;
  }

  public void setBaseType(String baseType) {
    this.baseType = baseType;
  }

  public ServiceSpecificationRequest toscaModelURL(String toscaModelURL) {
    this.toscaModelURL = toscaModelURL;
    return this;
  }

   /**
   * Additional attribute (not in the TMF API) - extended through @type - toscaModelURL
   * @return toscaModelURL
  **/
  @ApiModelProperty(value = "Additional attribute (not in the TMF API) - extended through @type - toscaModelURL")
  public String getToscaModelURL() {
    return toscaModelURL;
  }

  public void setToscaModelURL(String toscaModelURL) {
    this.toscaModelURL = toscaModelURL;
  }

  public ServiceSpecificationRequest toscaResourceName(String toscaResourceName) {
    this.toscaResourceName = toscaResourceName;
    return this;
  }

   /**
   * Additional attribute (not in the TMF API) - extended through @type - toscaResourceName
   * @return toscaResourceName
  **/
  @ApiModelProperty(value = "Additional attribute (not in the TMF API) - extended through @type - toscaResourceName")
  public String getToscaResourceName() {
    return toscaResourceName;
  }

  public void setToscaResourceName(String toscaResourceName) {
    this.toscaResourceName = toscaResourceName;
  }

  public ServiceSpecificationRequest category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Additional attribute - extended through @type - category Please note that this attribute is managed in TMF - in future release we&#39;ll introduce category resource
   * @return category
  **/
  @ApiModelProperty(value = "Additional attribute - extended through @type - category Please note that this attribute is managed in TMF - in future release we'll introduce category resource")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ServiceSpecificationRequest subcategory(String subcategory) {
    this.subcategory = subcategory;
    return this;
  }

   /**
   * Additional attribute - extended through @type - category Please note that this attribute is managed in TMF - in future release we&#39;ll introduce category resource
   * @return subcategory
  **/
  @ApiModelProperty(value = "Additional attribute - extended through @type - category Please note that this attribute is managed in TMF - in future release we'll introduce category resource")
  public String getSubcategory() {
    return subcategory;
  }

  public void setSubcategory(String subcategory) {
    this.subcategory = subcategory;
  }

  public ServiceSpecificationRequest version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Service specification version
   * @return version
  **/
  @ApiModelProperty(value = "Service specification version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ServiceSpecificationRequest lifecycleStatus(LifecycleStatusValues lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
    return this;
  }

   /**
   * Get lifecycleStatus
   * @return lifecycleStatus
  **/
  @ApiModelProperty(value = "")
  public LifecycleStatusValues getLifecycleStatus() {
    return lifecycleStatus;
  }

  public void setLifecycleStatus(LifecycleStatusValues lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
  }

  public ServiceSpecificationRequest targetServiceSchema(TargetServiceSchemaRef targetServiceSchema) {
    this.targetServiceSchema = targetServiceSchema;
    return this;
  }

   /**
   * Get targetServiceSchema
   * @return targetServiceSchema
  **/
  @ApiModelProperty(value = "")
  public TargetServiceSchemaRef getTargetServiceSchema() {
    return targetServiceSchema;
  }

  public void setTargetServiceSchema(TargetServiceSchemaRef targetServiceSchema) {
    this.targetServiceSchema = targetServiceSchema;
  }

  public ServiceSpecificationRequest attachment(List<Attachment> attachment) {
    this.attachment = attachment;
    return this;
  }

  public ServiceSpecificationRequest addAttachmentItem(Attachment attachmentItem) {
    if (this.attachment == null) {
      this.attachment = new ArrayList<Attachment>();
    }
    this.attachment.add(attachmentItem);
    return this;
  }

   /**
   * Get attachment
   * @return attachment
  **/
  @ApiModelProperty(value = "")
  public List<Attachment> getAttachment() {
    return attachment;
  }

  public void setAttachment(List<Attachment> attachment) {
    this.attachment = attachment;
  }

  public ServiceSpecificationRequest relatedParty(List<RelatedPartyRef> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public ServiceSpecificationRequest addRelatedPartyItem(RelatedPartyRef relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<RelatedPartyRef>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

   /**
   * Get relatedParty
   * @return relatedParty
  **/
  @ApiModelProperty(value = "")
  public List<RelatedPartyRef> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedPartyRef> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public ServiceSpecificationRequest resourceSpecification(List<ResourceSpecificationRef> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
    return this;
  }

  public ServiceSpecificationRequest addResourceSpecificationItem(ResourceSpecificationRef resourceSpecificationItem) {
    if (this.resourceSpecification == null) {
      this.resourceSpecification = new ArrayList<ResourceSpecificationRef>();
    }
    this.resourceSpecification.add(resourceSpecificationItem);
    return this;
  }

   /**
   * Get resourceSpecification
   * @return resourceSpecification
  **/
  @ApiModelProperty(value = "")
  public List<ResourceSpecificationRef> getResourceSpecification() {
    return resourceSpecification;
  }

  public void setResourceSpecification(List<ResourceSpecificationRef> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
  }

  public ServiceSpecificationRequest serviceSpecCharacteristic(List<ServiceSpecCharacteristicRequest> serviceSpecCharacteristic) {
    this.serviceSpecCharacteristic = serviceSpecCharacteristic;
    return this;
  }

  public ServiceSpecificationRequest addServiceSpecCharacteristicItem(ServiceSpecCharacteristicRequest serviceSpecCharacteristicItem) {
    if (this.serviceSpecCharacteristic == null) {
      this.serviceSpecCharacteristic = new ArrayList<ServiceSpecCharacteristicRequest>();
    }
    this.serviceSpecCharacteristic.add(serviceSpecCharacteristicItem);
    return this;
  }

   /**
   * Get serviceSpecCharacteristic
   * @return serviceSpecCharacteristic
  **/
  @ApiModelProperty(value = "")
  public List<ServiceSpecCharacteristicRequest> getServiceSpecCharacteristic() {
    return serviceSpecCharacteristic;
  }

  public void setServiceSpecCharacteristic(List<ServiceSpecCharacteristicRequest> serviceSpecCharacteristic) {
    this.serviceSpecCharacteristic = serviceSpecCharacteristic;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceSpecificationRequest serviceSpecificationRequest = (ServiceSpecificationRequest) o;
    return Objects.equals(this.name, serviceSpecificationRequest.name) &&
        Objects.equals(this.description, serviceSpecificationRequest.description) &&
        Objects.equals(this.type, serviceSpecificationRequest.type) &&
        Objects.equals(this.schemaLocation, serviceSpecificationRequest.schemaLocation) &&
        Objects.equals(this.baseType, serviceSpecificationRequest.baseType) &&
        Objects.equals(this.toscaModelURL, serviceSpecificationRequest.toscaModelURL) &&
        Objects.equals(this.toscaResourceName, serviceSpecificationRequest.toscaResourceName) &&
        Objects.equals(this.category, serviceSpecificationRequest.category) &&
        Objects.equals(this.subcategory, serviceSpecificationRequest.subcategory) &&
        Objects.equals(this.version, serviceSpecificationRequest.version) &&
        Objects.equals(this.lifecycleStatus, serviceSpecificationRequest.lifecycleStatus) &&
        Objects.equals(this.targetServiceSchema, serviceSpecificationRequest.targetServiceSchema) &&
        Objects.equals(this.attachment, serviceSpecificationRequest.attachment) &&
        Objects.equals(this.relatedParty, serviceSpecificationRequest.relatedParty) &&
        Objects.equals(this.resourceSpecification, serviceSpecificationRequest.resourceSpecification) &&
        Objects.equals(this.serviceSpecCharacteristic, serviceSpecificationRequest.serviceSpecCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, type, schemaLocation, baseType, toscaModelURL, toscaResourceName, category, subcategory, version, lifecycleStatus, targetServiceSchema, attachment, relatedParty, resourceSpecification, serviceSpecCharacteristic);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceSpecificationRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    toscaModelURL: ").append(toIndentedString(toscaModelURL)).append("\n");
    sb.append("    toscaResourceName: ").append(toIndentedString(toscaResourceName)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    subcategory: ").append(toIndentedString(subcategory)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    targetServiceSchema: ").append(toIndentedString(targetServiceSchema)).append("\n");
    sb.append("    attachment: ").append(toIndentedString(attachment)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    resourceSpecification: ").append(toIndentedString(resourceSpecification)).append("\n");
    sb.append("    serviceSpecCharacteristic: ").append(toIndentedString(serviceSpecCharacteristic)).append("\n");
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

