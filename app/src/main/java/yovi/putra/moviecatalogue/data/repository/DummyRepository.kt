package yovi.putra.moviecatalogue.data.repository

import com.google.gson.Gson
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse

object DummyRepository {

    fun getMovieDetail() : MovieDetailResponse {
        return Gson().fromJson("" +
                "{\n" +
                "  \"adult\":false,\n" +
                "  \"backdrop_path\":\"/zTxHf9iIOCqRbxvl8W5QYKrsMLq.jpg\",\n" +
                "  \"belongs_to_collection\":{\n" +
                "    \"id\":495527,\n" +
                "    \"name\":\"Jumanji Collection\",\n" +
                "    \"poster_path\":\"/6sjMsBcIuqU44GpG5tL33KUFOQR.jpg\",\n" +
                "    \"backdrop_path\":\"/9PCsWrw1GvrZkrd1GCxRqscgZu0.jpg\"\n" +
                "  },\n" +
                "  \"budget\":125000000,\n" +
                "  \"genres\":[\n" +
                "    {\n" +
                "      \"id\":28,\n" +
                "      \"name\":\"Action\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":12,\n" +
                "      \"name\":\"Adventure\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":35,\n" +
                "      \"name\":\"Comedy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":14,\n" +
                "      \"name\":\"Fantasy\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"homepage\":\"http://jumanjimovie.com\",\n" +
                "  \"id\":512200,\n" +
                "  \"imdb_id\":\"tt7975244\",\n" +
                "  \"original_language\":\"en\",\n" +
                "  \"original_title\":\"Jumanji: The Next Level\",\n" +
                "  \"overview\":\"As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world’s most dangerous game.\",\n" +
                "  \"popularity\":356.765,\n" +
                "  \"poster_path\":\"/jyw8VKYEiM1UDzPB7NsisUgBeJ8.jpg\",\n" +
                "  \"production_companies\":[\n" +
                "    {\n" +
                "      \"id\":5,\n" +
                "      \"logo_path\":\"/71BqEFAF4V3qjjMPCpLuyJFB9A.png\",\n" +
                "      \"name\":\"Columbia Pictures\",\n" +
                "      \"origin_country\":\"US\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":73669,\n" +
                "      \"logo_path\":\"/usfW3lL4cL8eatNAlhfRrryyKkK.png\",\n" +
                "      \"name\":\"Seven Bucks Productions\",\n" +
                "      \"origin_country\":\"US\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":40268,\n" +
                "      \"logo_path\":\"/shdAxUj8uF6exNLrF0kSqYVxCzG.png\",\n" +
                "      \"name\":\"HartBeat Productions\",\n" +
                "      \"origin_country\":\"US\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":53462,\n" +
                "      \"logo_path\":null,\n" +
                "      \"name\":\"Matt Tolmach Productions\",\n" +
                "      \"origin_country\":\"US\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":2550,\n" +
                "      \"logo_path\":null,\n" +
                "      \"name\":\"Teitler Film\",\n" +
                "      \"origin_country\":\"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":34,\n" +
                "      \"logo_path\":\"/GagSvqWlyPdkFHMfQ3pNq6ix9P.png\",\n" +
                "      \"name\":\"Sony Pictures\",\n" +
                "      \"origin_country\":\"US\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"production_countries\":[\n" +
                "    {\n" +
                "      \"iso_3166_1\":\"US\",\n" +
                "      \"name\":\"United States of America\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"release_date\":\"2019-12-04\",\n" +
                "  \"revenue\":72256955,\n" +
                "  \"runtime\":123,\n" +
                "  \"spoken_languages\":[\n" +
                "    {\n" +
                "      \"iso_639_1\":\"en\",\n" +
                "      \"name\":\"English\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\":\"Released\",\n" +
                "  \"tagline\":\"\",\n" +
                "  \"title\":\"Jumanji: The Next Level\",\n" +
                "  \"video\":false,\n" +
                "  \"vote_average\":6.7,\n" +
                "  \"vote_count\":683\n" +
                "}", MovieDetailResponse::class.java)
    }

    fun getTVShowDetail() : TVShowDetailResponse {
        return Gson().fromJson("{\n" +
                "  \"backdrop_path\":\"/bKETHQDD3QoIRTMOP4dfKwisL3g.jpg\",\n" +
                "  \"created_by\":[\n" +
                "    {\n" +
                "      \"id\":1219517,\n" +
                "      \"credit_id\":\"5b66d0f10e0a267eed0a3f6d\",\n" +
                "      \"name\":\"Lauren Schmidt\",\n" +
                "      \"gender\":1,\n" +
                "      \"profile_path\":null\n" +
                "    }\n" +
                "  ],\n" +
                "  \"episode_run_time\":[\n" +
                "    60\n" +
                "  ],\n" +
                "  \"first_air_date\":\"2019-12-20\",\n" +
                "  \"genres\":[\n" +
                "    {\n" +
                "      \"id\":10765,\n" +
                "      \"name\":\"Sci-Fi & Fantasy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":18,\n" +
                "      \"name\":\"Drama\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"homepage\":\"https://www.netflix.com/title/80189685\",\n" +
                "  \"id\":71912,\n" +
                "  \"in_production\":true,\n" +
                "  \"languages\":[\n" +
                "    \"en\"\n" +
                "  ],\n" +
                "  \"last_air_date\":\"2019-12-20\",\n" +
                "  \"last_episode_to_air\":{\n" +
                "    \"air_date\":\"2019-12-20\",\n" +
                "    \"episode_number\":8,\n" +
                "    \"id\":1954618,\n" +
                "    \"name\":\"Much More\",\n" +
                "    \"overview\":\"A terrifying pack of foes lays Geralt low. Yennefer and her fellow mages prepare to fight back. A shaken Ciri depends on the kindness of a stranger.\",\n" +
                "    \"production_code\":\"\",\n" +
                "    \"season_number\":1,\n" +
                "    \"show_id\":71912,\n" +
                "    \"still_path\":\"/ulV6ju6raewEYAH9ejc2ECMrc4r.jpg\",\n" +
                "    \"vote_average\":9.4,\n" +
                "    \"vote_count\":5\n" +
                "  },\n" +
                "  \"name\":\"The Witcher\",\n" +
                "  \"next_episode_to_air\":null,\n" +
                "  \"networks\":[\n" +
                "    {\n" +
                "      \"name\":\"Netflix\",\n" +
                "      \"id\":213,\n" +
                "      \"logo_path\":\"/wwemzKWzjKYJFfCeiB57q3r4Bcm.png\",\n" +
                "      \"origin_country\":\"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"number_of_episodes\":8,\n" +
                "  \"number_of_seasons\":1,\n" +
                "  \"origin_country\":[\n" +
                "    \"PL\",\n" +
                "    \"US\"\n" +
                "  ],\n" +
                "  \"original_language\":\"en\",\n" +
                "  \"original_name\":\"The Witcher\",\n" +
                "  \"overview\":\"Geralt of Rivia, a mutated monster-hunter for hire, journeys toward his destiny in a turbulent world where people often prove more wicked than beasts.\",\n" +
                "  \"popularity\":367.302,\n" +
                "  \"poster_path\":\"/zrPpUlehQaBf8YX2NrVrKK8IEpf.jpg\",\n" +
                "  \"production_companies\":[\n" +
                "    {\n" +
                "      \"id\":19857,\n" +
                "      \"logo_path\":null,\n" +
                "      \"name\":\"Sean Daniel Company\",\n" +
                "      \"origin_country\":\"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":36414,\n" +
                "      \"logo_path\":null,\n" +
                "      \"name\":\"Platige Image\",\n" +
                "      \"origin_country\":\"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":114032,\n" +
                "      \"logo_path\":null,\n" +
                "      \"name\":\"Pioneer Stilking Films\",\n" +
                "      \"origin_country\":\"GB\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"seasons\":[\n" +
                "    {\n" +
                "      \"air_date\":\"2019-12-20\",\n" +
                "      \"episode_count\":8,\n" +
                "      \"id\":88731,\n" +
                "      \"name\":\"Season 1\",\n" +
                "      \"overview\":\"\",\n" +
                "      \"poster_path\":\"/8mPAbhdxldeq59TS3SIRu4P0nD0.jpg\",\n" +
                "      \"season_number\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\":\"Returning Series\",\n" +
                "  \"type\":\"Scripted\",\n" +
                "  \"vote_average\":8.3,\n" +
                "  \"vote_count\":206\n" +
                "}", TVShowDetailResponse::class.java)
    }
}