package test.gyatsina.wikiatask.api;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import test.gyatsina.wikiatask.models.DetailedItemById;
import test.gyatsina.wikiatask.models.DetailedItemsContainer;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;

public interface WikiaApi {
    /*
    Usually backend request looks different from .php page
    So in general case "/" will be replaced with valuable mothod name, f.i. getList
     */
    @GET("/")
    void getGamingItemsList(@Query("controller") String controller, @Query("method") String getListMethod,
                            @Query("hub") String hub, @Query("lang") String lang,
                            @Query("batch") int batch,
                            Callback<ItemsList<GamingItemInList>> callback);

//    http://www.wikia.com/wikia.php?controller=WikisApi&method=getDetails&ids=3125,490
    @GET("/")
    void getDetailedItemsById(@Query("controller") String controller, @Query("method") String getListMethod,
                        @Query("ids") String ids,
                        Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>> callback);

//    /**
//     * Get all categories
//     */
//    @GET("/category")
//    void getCategoriesList(Callback<ItemsListParcelable<Category>> callback);
//
//    /**
//     * Get all news
//     */
//    @GET("/news")
//    void getNewsList(Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news")
//    void getNewsNextPage(@Query("offset") int newOffset, Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news")
//    void getNewsByCategoryId(@Query("cid") String categoryId, Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news")
//    void getNewsNextPageByCategoryId(@Query("cid") String categoryId, @Query("offset") int offset, Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news")
//    void getNewsByLocation(@Query("what") String what, @Query("where") String where, Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news")
//    void getNewsNextPageByLocation(@Query("what") String what, @Query("where") String where, @Query("offset") int offset, Callback<ItemsImagesListParcelable<NewsItemInList>> callback);
//
//    @GET("/news/{newsId}")
//    void getNewsById(@Path("newsId") String newsId, Callback<SingleNews> callback);
//
//    @GET("/logo")
//    void getLogos(Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo")
//    void getLogosNextPage(@Query("offset") int newOffset, Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo")
//    void getLogosByCategoryId(@Query("cid") String categoryId, Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo")
//    void getLogosNextPageByCategoryId(@Query("cid") String categoryId, @Query("offset") int newOffset, Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo")
//    void getLogosByLocation(@Query("what") String what, @Query("where") String where, Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo")
//    void getLogosNextPageByLocation(@Query("what") String what, @Query("where") String where, @Query("offset") int offset, Callback<ItemsListParcelable<LogoItemInList>> callback);
//
//    @GET("/logo/{logoId}")
//    void getLogoById(@Path("logoId") String logoId, Callback<SingleLogo> callback);
//
//    @GET("/product")
//    void getProductsByLogoId(@Query("logo_id") String logoId, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsNextPageByLogoId(@Query("logo_id") String logoId, @Query("offset") int newOffset, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/location")
//    void getLocationsByLogoId(@Query("logo_id") String logoId, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getLocationsNextPageByLogoId(@Query("logo_id") String logoId, @Query("offset") int newOffset, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location/{location_id}")
//    void getLocationById(@Path("location_id") String locationId, Callback<SinglePoi> callback);
//
//    @GET("/location")
//    void getLocations(Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getLocationsNextPage(@Query("offset") int newOffset, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getLocationsByCategoryId(@Query("cid") String categoryId, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getLocationsNextPageByCategoryId(@Query("cid") String categoryId, @Query("offset") int newOffset, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getPoiByLocation(@Query("what") String what, @Query("where") String where, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/location")
//    void getPoiNextPageByLocation(@Query("what") String what, @Query("where") String where, @Query("offset") int offset, Callback<ItemsImagesListParcelable<PoiInList>> callback);
//
//    @GET("/offer")
//    void getOffers(Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer")
//    void getOffersNextPage(@Query("offset") int newOffset, Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer")
//    void getOffersByCategoryId(@Query("cid") String categoryId, Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer")
//    void getOffersNextPageByCategoryId(@Query("cid") String categoryId, @Query("offset") int newOffset, Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer")
//    void getOffersByLocation(@Query("what") String what, @Query("where") String where, Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer")
//    void getOffersNextPageByLocation(@Query("what") String what, @Query("where") String where, @Query("offset") int offset, Callback<ItemsListParcelable<OfferItemInList>> callback);
//
//    @GET("/offer/{offer_id}")
//    void getOfferById(@Path("offer_id") String offerId, Callback<ItemHolder<SingleOffer>> callback);
//
//    @GET("/product")
//    void getProducts(Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsNextPage(@Query("offset") int newOffset, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsByCategoryId(@Query("cid") String categoryId, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsNextPageByCategoryId(@Query("cid") String categoryId, @Query("offset") int newOffset, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsByLocation(@Query("what") String what, @Query("where") String where, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product")
//    void getProductsNextPageByLocation(@Query("what") String what, @Query("where") String where, @Query("offset") int newOffset, Callback<ItemsListParcelable<ProductItemInList>> callback);
//
//    @GET("/product/{product_id}")
//    void getProductById(@Path("product_id") String productId, Callback<ItemHolder<SingleProduct>> callback);
//
//    @GET("/search")
//    void search(@Query("what") String what, @Query("where") String where, Callback<BigCategoryItem> callback);
//
//    @POST("/contact")
//    void contact(@Query("name") String name, @Query("phone") String phone, @Query("mail") String mail, @Query("subject") String subject, @Query("message") String message, Callback<PostResponse> status);
}
