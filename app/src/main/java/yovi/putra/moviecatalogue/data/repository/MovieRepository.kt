package yovi.putra.moviecatalogue.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import yovi.putra.moviecatalogue.data.entity.Movie

object MovieRepository {

    fun getMovieById(id: Int) : Movie? = getMovies().find { it.id == id }

    fun getMovies(): ArrayList<Movie> {
        val movies = "[\n" +
                "  {\n" +
                "    \"id\": 290859,\n" +
                "    \"title\": \"Terminator: Dark Fate\",\n" +
                "    \"poster_path\": \"/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Terminator: Dark Fate\",\n" +
                "    \"vote_average\": 6.6,\n" +
                "    \"overview\": \"More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope.\",\n" +
                "    \"release_date\": \"2019-11-01\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 501170,\n" +
                "    \"poster_path\": \"/p69QzIBbN06aTYqRRiCOY1emNBh.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Doctor Sleep\",\n" +
                "    \"title\": \"Doctor Sleep\",\n" +
                "    \"vote_average\": 7.1,\n" +
                "    \"overview\": \"A traumatized, alcoholic Dan Torrance meets Abra, a kid who also has the ability to \\\"shine.\\\" He tries to protect her from the True Knot, a cult whose goal is to feed off of people like them in order to remain immortal.\",\n" +
                "    \"release_date\": \"2019-11-08\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 480105,\n" +
                "    \"poster_path\": \"/g4z7mDmJmx23vsVg6XNWcnXb6gc.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"47 Meters Down: Uncaged\",\n" +
                "    \"title\": \"47 Meters Down: Uncaged\",\n" +
                "    \"vote_average\": 5.1,\n" +
                "    \"overview\": \"A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean's deadliest shark species.\",\n" +
                "    \"release_date\": \"2019-08-16\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 338967,\n" +
                "    \"poster_path\": \"/pIcV8XXIIvJCbtPoxF9qHMKdRr2.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Zombieland: Double Tap\",\n" +
                "    \"title\": \"Zombieland: Double Tap\",\n" +
                "    \"vote_average\": 7.4,\n" +
                "    \"overview\": \"Columbus, Tallahassee, Wichita, and Little Rock move to the American heartland as they face off against evolved zombies, fellow survivors, and the growing pains of the snarky makeshift family.\",\n" +
                "    \"release_date\": \"2019-10-18\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 330457,\n" +
                "    \"poster_path\": \"/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Frozen II\",\n" +
                "    \"title\": \"Frozen II\",\n" +
                "    \"vote_average\": 7.2,\n" +
                "    \"overview\": \"Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.\",\n" +
                "    \"release_date\": \"2019-11-22\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg\",\n" +
                "    \"id\": 496243,\n" +
                "    \"original_language\": \"ko\",\n" +
                "    \"original_title\": \"기생충\",\n" +
                "    \"title\": \"Parasite\",\n" +
                "    \"vote_average\": 8.5,\n" +
                "    \"overview\": \"All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.\",\n" +
                "    \"release_date\": \"2019-10-11\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/xL1yNzwGSXfassYpt13hXADpz6R.jpg\",\n" +
                "    \"id\": 522162,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Midway\",\n" +
                "    \"title\": \"Midway\",\n" +
                "    \"vote_average\": 5.3,\n" +
                "    \"overview\": \"The story of the soldiers and aviators who helped turn the tide of the Second World War during the iconic Battle of Midway in June 1942.\",\n" +
                "    \"release_date\": \"2019-11-08\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/s9Ig9aoORT9jCuUDOCX4j1nNke6.jpg\",\n" +
                "    \"id\": 549053,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Last Christmas\",\n" +
                "    \"title\": \"Last Christmas\",\n" +
                "    \"vote_average\": 7.9,\n" +
                "    \"overview\": \"Kate is a young woman subscribed to bad decisions. Her last date with disaster? That of having accepted to work as Santa's elf for a department store. However, she meets Tom there. Her life takes a new turn. For Kate, it seems too good to be true.\",\n" +
                "    \"release_date\": \"2019-11-08\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/9kOtMOUa5HrOiIG2Z9Anv7M8AbY.jpg\",\n" +
                "    \"id\": 511987,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Crawl\",\n" +
                "    \"title\": \"Crawl\",\n" +
                "    \"vote_average\": 6.1,\n" +
                "    \"overview\": \"When a huge hurricane hits her hometown in Florida, Haley ignores evacuation orders to look for her father. After finding him badly wounded, both are trapped by the flood. With virtually no time to escape the storm, they discover that rising water levels are the least of their problems.\",\n" +
                "    \"release_date\": \"2019-07-12\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/fjmMu9fpqMMF17mCyLhNfkagKB0.jpg\",\n" +
                "    \"id\": 578189,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Black and Blue\",\n" +
                "    \"title\": \"Black and Blue\",\n" +
                "    \"vote_average\": 5,\n" +
                "    \"overview\": \"Exposure follows a rookie Detroit African-American female cop who stumbles upon corrupt officers who are murdering a drug dealer, an incident captured by her body cam. They pursue her through the night in an attempt to destroy the footage, but to make matters worse, they've tipped off a criminal gang that she's responsible for the dealer's death.\",\n" +
                "    \"release_date\": \"2019-10-25\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/zBhv8rsLOfpFW2M5b6wW78Uoojs.jpg\",\n" +
                "    \"id\": 540901,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"Hustlers\",\n" +
                "    \"title\": \"Hustlers\",\n" +
                "    \"vote_average\": 6.2,\n" +
                "    \"overview\": \"A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.\",\n" +
                "    \"release_date\": \"2019-09-13\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"poster_path\": \"/taR7KaMNiR0JJ8TfPYgUTDUFD6y.jpg\",\n" +
                "    \"id\": 511322,\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"original_title\": \"The Good Liar\",\n" +
                "    \"title\": \"The Good Liar\",\n" +
                "    \"vote_average\": 0,\n" +
                "    \"overview\": \"An aging con artist cannot believe his luck when he meets a wealthy widow and marks her as his next target.  But she hides a secret of her own.\",\n" +
                "    \"release_date\": \"2019-11-15\"\n" +
                "  }\n" +
                "]"
        val responseType = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(movies, responseType)
    }
}