package yovi.putra.moviecatalogue.data.entity

class TVShowItem(
    val id: Int,
    val vote_count: Int,
    val original_name: String,
    val vote_average: Double,
    val name: String,
    val popularity: Double,
    val poster_path: String,
    val original_language: String,
    val overview: String
)

/* Response */
// List TVShow
data class TVShowListResponse(
    val results: List<TVShowItem>,
    val page: Int,
    val total_results: Int,
    val dates: DateRange,
    val total_pages: Int
)

// Detail TVShow
data class TVShowDetailResponse(
    val backdrop_path: String,
    val homepage: String,
    val id: Int,
    val original_language: String,
    val original_name: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val origin_country: List<String>,
    val first_air_date: String,
    val status: String,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<Genres>,
    val languages: List<String>,
    val seasons: List<Session>,
    val created_by: List<CreateBy>
)

data class CreateBy(
    val name: String
)

data class Session(
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)

data class Genres(
    val id: Int,
    val name: String
)