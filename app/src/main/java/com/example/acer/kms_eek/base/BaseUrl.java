package com.example.acer.kms_eek.base;

/**
 * Created by acer on 2/3/2020.
 */

public class BaseUrl {
    //private static final String domain = "https://dayakbahau.000webhostapp.com/api/";
    private static final String domain = "https://dayakbahau.000webhostapp.com/api/";
    public static final String url_berita = domain+"get_berita.php";

    public static final String token = "FMOUhu3bTnszsleLXWsPNsVxjsf2CNKKJkf6Xa4lgzVBLdvHssokvjM6JdnASjdRys14d3cFW8sXMrbW";
    private static String keyCode = "?token="+token;
    //    public static final String DOMAIN = "cbtit2.000webhostapp.com";
    public static final String DOMAIN = "192.168.43.84/kmseek/public";
    public static final String BASE_URL = "http://" + DOMAIN + "/";
    //public static final String URL_ALL_KATEGORI = BASE_URL + "posts"+keyCode;
    //public static final String URL_PAGINATION_POST = BASE_URL + "posts/pagination";

    public static final String URL_ALL_HISTORY = BASE_URL + "history";
}
