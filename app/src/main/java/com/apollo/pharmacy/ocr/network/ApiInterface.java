package com.apollo.pharmacy.ocr.network;

import com.apollo.pharmacy.ocr.activities.mposstoresetup.model.StoreListResponseModel;
import com.apollo.pharmacy.ocr.controller.GetStoreInfoRequest;
import com.apollo.pharmacy.ocr.model.AddFCMTokenRequest;
import com.apollo.pharmacy.ocr.model.BatchListRequest;
import com.apollo.pharmacy.ocr.model.BatchListResponse;
import com.apollo.pharmacy.ocr.model.Category_request;
import com.apollo.pharmacy.ocr.model.Categorylist_Response;
import com.apollo.pharmacy.ocr.model.DeviceRegistrationRequest;
import com.apollo.pharmacy.ocr.model.DeviceRegistrationResponse;
import com.apollo.pharmacy.ocr.model.GetImageRes;
import com.apollo.pharmacy.ocr.model.GetProductListResponse;
import com.apollo.pharmacy.ocr.model.GetStoreInfoResponse;
import com.apollo.pharmacy.ocr.model.Global_api_request;
import com.apollo.pharmacy.ocr.model.Global_api_response;
import com.apollo.pharmacy.ocr.model.ItemSearchRequest;
import com.apollo.pharmacy.ocr.model.ItemSearchResponse;
import com.apollo.pharmacy.ocr.model.Meta;
import com.apollo.pharmacy.ocr.model.ModelMobileNumVerify;
import com.apollo.pharmacy.ocr.model.NewSearchapirequest;
import com.apollo.pharmacy.ocr.model.NewSearchapiresponse;
import com.apollo.pharmacy.ocr.model.OrderHistoryRequest;
import com.apollo.pharmacy.ocr.model.OrderHistoryResponse;
import com.apollo.pharmacy.ocr.model.OrderPushingRequest;
import com.apollo.pharmacy.ocr.model.OrderPushingRequest_new;
import com.apollo.pharmacy.ocr.model.OrderPushingResponse;
import com.apollo.pharmacy.ocr.model.OrderPushingResponse_new;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeRequest;
import com.apollo.pharmacy.ocr.model.PhonePayQrCodeResponse;
import com.apollo.pharmacy.ocr.model.PinelabTransaction_cancelresponse;
import com.apollo.pharmacy.ocr.model.Pinelabs_paymenttransaction_response;
import com.apollo.pharmacy.ocr.model.Pinelabs_transaction_payment_request;
import com.apollo.pharmacy.ocr.model.Pinelabsrequest;
import com.apollo.pharmacy.ocr.model.Pinelabtransaction_cancelrequest;
import com.apollo.pharmacy.ocr.model.PinepayrequestResult;
import com.apollo.pharmacy.ocr.model.PlaceOrderReqModel;
import com.apollo.pharmacy.ocr.model.PlaceOrderResModel;
import com.apollo.pharmacy.ocr.model.PortFolioModel;
import com.apollo.pharmacy.ocr.model.ProductSrearchResponse;
import com.apollo.pharmacy.ocr.model.Reddemponits_getpoints_response;
import com.apollo.pharmacy.ocr.model.Reddemponits_sendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_checkvoucher_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_checkvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_getpoints_requst;
import com.apollo.pharmacy.ocr.model.Redeempoints_redeemvoucher_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_redeemvoucher_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_resendotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_resendotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_sendotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_response;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_retry_request;
import com.apollo.pharmacy.ocr.model.Redeempoints_validateotp_retry_response;
import com.apollo.pharmacy.ocr.model.Searchsuggestionrequest;
import com.apollo.pharmacy.ocr.model.Searchsuggestionresponse;
import com.apollo.pharmacy.ocr.model.Send_Sms_Request;
import com.apollo.pharmacy.ocr.model.Send_Sms_Response;
import com.apollo.pharmacy.ocr.model.ServicabilityResponse;
import com.apollo.pharmacy.ocr.model.ServiceAvailabilityRequest;
import com.apollo.pharmacy.ocr.model.StoreLocatorResponse;
import com.apollo.pharmacy.ocr.model.UploadImageRequest;
import com.apollo.pharmacy.ocr.model.UploadImageResponse;
import com.apollo.pharmacy.ocr.model.UserAddress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiInterface {
    @POST("v3/?")
    @FormUrlEncoded
    Call<ModelMobileNumVerify> verifyMobileNumber(@Field("method") String method, @Field("api_key") String api_key, @Field("to") String to, @Field("sender") String sender, @Field("message") String message);

    @GET("apollo_api.php")
    @Headers("Authorization: bearer dp50h14gpxtqf8gi1ggnctqcrr0io6ms")
    Call<GetProductListResponse> getProductListByCategIdLoadMore(@Query("category_id") int id, @Query("page_id") String pageId, @Query("type") String type);

    @GET("storelocationsapi.php?token=6edc284b5929c718878e57861e452ea575fc9f6238ac9f2852c7157184aea5df")
    Call<List<StoreLocatorResponse>> getStoreLocatorApi();

    //product Category production Api......
    @POST("categoryproducts_api.php")
    @Headers("authorization: Bearer 2o1kd4bjapqifpb27fy7tnbivu8bqo1d")
    Observable<GetProductListResponse> getProductListByCategId(@Body Category_request request);

    @POST("Onlineorder.svc/Place_orders")
    Call<OrderPushingResponse> orderpushing(@Body OrderPushingRequest request);

    @GET("Default.aspx?")
    Call<Object> paytmtransactionapi(@Query("siteid") String siteid);

    @POST("fcm/add")
    Call<Meta> addFcmToken(@Body AddFCMTokenRequest request);

    @POST("API/CloudBasedIntegration/V1/CancelTransaction")
    Call<PinelabTransaction_cancelresponse> pinelabscanceltransaction(@Body Pinelabtransaction_cancelrequest request);

    @POST("API/CloudBasedIntegration/V1/UploadBilledTransaction")
    Call<PinepayrequestResult> pinelabstransaction(@Body Pinelabsrequest request);

    @POST("API/CloudBasedIntegration/V1/GetCloudBasedTxnStatus")
    Call<Pinelabs_paymenttransaction_response> pinelabstpaymenttransaction(@Body Pinelabs_transaction_payment_request request);

    @POST("userInfo/addUserAddress")
    Call<Meta> addAddress(@Body UserAddress address);

    @GET("userInfo/getUserAddress/{mobile}")
    Call<ArrayList<UserAddress>> getUserAddressList(@Path("mobile") String mobile);

    @PUT("userInfo/editAddress")
    Call<Meta> editAddress(@Body UserAddress address);

    @PUT("userInfo/deleteAddress/{id}")
    Call<Meta> deleteAddress(@Path("id") Integer id);

    @GET("https://jsonblob.com/api/jsonBlob/887622755529539584")//webresources/OrderHistory/getHistory
    Call<List<OrderHistoryResponse>> getOrderHistory();//@Body OrderHistoryRequest request

    @GET("Customer/GetByMobile?")
    @Headers({"APIKey: 2B577C3C4C144160A5FD4885F7BA53A4", "AccessToken: 03F80DDA69A84382A8AC0E108270972F"})
    Call<PortFolioModel> getPortFolio(@Query("mobilenumber") String mob_num, @Query("GetTierBenefits") String GetTierBenefits, @Query("BusinessUnit") String BusinessUnit);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Reddemponits_getpoints_response> getredeempoints(@Body Redeempoints_getpoints_requst request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Reddemponits_sendotp_response> redeempoints_sendotp(@Body Redeempoints_sendotp_request request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Redeempoints_resendotp_response> redeempoints_resendotp(@Body Redeempoints_resendotp_request request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Redeempoints_validateotp_response> redeempoints_validateotp(@Body Redeempoints_validateotp_request request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Redeempoints_validateotp_retry_response> redeempoints_validateotp_retry(@Body Redeempoints_validateotp_retry_request request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Redeempoints_checkvoucher_response> redeempoints_checkvoucher(@Body Redeempoints_checkvoucher_request request);

    @POST("OA/OneApolloService.svc/ONEAPOLLOTRANS")
    Call<Redeempoints_redeemvoucher_response> redeempoints_redeemvoucher(@Body Redeempoints_redeemvoucher_request request);

    @POST("OrderPlace.svc/PLACE_ORDERS")
    Call<OrderPushingResponse_new> orderpushing_new(@Body OrderPushingRequest_new request, @Header("Token") String auth);

    //search suggestion api....
    @POST("popcsrchss_api.php")
    @Headers("authorization: Bearer 2o1kd4bjapqifpb27fy7tnbivu8bqo1d")
    Call<Searchsuggestionresponse> searchsuggestion(@Body Searchsuggestionrequest request);

    //New Search Api.......
    @POST("popcsrchprd_api.php")
    @Headers("authorization: Bearer 2o1kd4bjapqifpb27fy7tnbivu8bqo1d")
    Call<NewSearchapiresponse> newsearchapi(@Body NewSearchapirequest request);

    //get global apis dynamically.........
    @POST("Self/VALIDATEVENDOR")
    Call<Global_api_response> get_global_apis(@Body Global_api_request request);

    //Sens Sms New Api......
    @POST("APSMS/APOLLO/SMS/SENDSMS")
    Call<Send_Sms_Response> send_sms_api(@Body Send_Sms_Request request);

    @GET
    Call<GetImageRes> getScannedPrescription(@Url String fileUrl);

    @POST("PLACE_ORDERS")
    Call<PlaceOrderResModel> PLACE_ORDER_SERVICE_CALL(@Header("token") String token, @Body PlaceOrderReqModel placeOrderReqModel);

    @GET
    Call<Categorylist_Response> getCategorylist(@Url String fileUrl);

    @POST
    Call<GetStoreInfoResponse> getStoreListService(@Url String url, @Body GetStoreInfoRequest request);

    @POST("GetDetailsByPincode")
    Call<ServicabilityResponse> get_servicability_api(@Body ServiceAvailabilityRequest request);

    @POST
    Call<UploadImageResponse> getUploadPrescriptionService(@Url String url, @Body UploadImageRequest request);

    @POST("http://20.197.57.170//rest/V1/searchapi")
    Call<List<ProductSrearchResponse>> productSearch(@Header("authorization") String token, @Body Object productSearchRequest);

    @POST(" http://online.apollopharmacy.org:51/EPOS/SalesTransactionService.svc/GetItemDetails")//ba8af54b-fa62-11eb-978a-d72b0c462c48
    Call<ItemSearchResponse> getSearchItemApiCall(@Body ItemSearchRequest itemSearchRequest);


    @GET("apollompos/Self/STORELIST")
    Call<StoreListResponseModel> GET_STORES_LIST();

    @POST(" http://online.apollopharmacy.org:51/EPOS/SalesTransactionService.svc/GetBatchDetails")//6009921b-fa66-11eb-978a-85578f7c5b29
    Call<BatchListResponse> GET_BATCH_LIST(@Body BatchListRequest batchListRequest);

    @POST("http://lms.apollopharmacy.org:8033/APK/apollompos/Self/Registration")
    Call<DeviceRegistrationResponse> deviceRegistration(@Body DeviceRegistrationRequest deviceRegistrationRequest);

    @POST("http://online.apollopharmacy.org:51/EPOS/WalletService.svc/GetQRCodePaymentDetails")
    Call<PhonePayQrCodeResponse> GET_PhonePay_Qr_Code(@Body PhonePayQrCodeRequest phonePayQrCodeRequest);

    @POST("http://online.apollopharmacy.org:51/EPOS/WalletService.svc/GetQRCodePaymentDetails")
    Call<PhonePayQrCodeResponse> GET_PhonePay_Qr_payment_Success(@Body PhonePayQrCodeResponse responseasRequest);


}
