package yovi.putra.moviecatalogue.data.repository

import com.google.gson.Gson
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.entity.TVShowItem

object DummyRepository {

    fun getMovieItem() : MovieItem {
        return Gson().fromJson("{\"adult\":false,\"backdrop_path\":\"/tJgmraUAmVlJPlq72yC76tKDyC4.jpg\",\"id\":530915,\"original_language\":\"en\",\"original_title\":\"1917\",\"overview\":\"At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake\\u0027s own brother among them.\",\"popularity\":222.017,\"poster_path\":\"/iZf0KyrE25z1sage4SYFLCCrMi9.jpg\",\"release_date\":\"2019-12-10\",\"title\":\"1917\",\"video\":false,\"vote_average\":8.1,\"vote_count\":1530}",
            MovieItem::class.java)
    }

    fun getTVShowItem() : TVShowItem {
        return Gson().fromJson(
            "{\n" +
                    "  \"original_name\":\"The Mandalorian\",\n" +
                    "  \"id\":82856,\n" +
                    "  \"name\":\"The Mandalorian\",\n" +
                    "  \"popularity\":759.563,\n" +
                    "  \"vote_count\":395,\n" +
                    "  \"vote_average\":7.9,\n" +
                    "  \"first_air_date\":\"2019-11-12\",\n" +
                    "  \"poster_path\":\"\\/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg\",\n" +
                    "  \"genre_ids\":[\n" +
                    "    10765,\n" +
                    "    10759\n" +
                    "  ],\n" +
                    "  \"original_language\":\"en\",\n" +
                    "  \"backdrop_path\":\"\\/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg\",\n" +
                    "  \"overview\":\"Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.\",\n" +
                    "  \"origin_country\":[\n" +
                    "    \"US\"\n" +
                    "  ]\n" +
                    "}",
            TVShowItem::class.java
        )
    }
}