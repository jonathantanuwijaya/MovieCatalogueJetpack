package yovi.putra.moviecatalogue.data.entity

data class MovieItem (
    val vote_count: Int,
    val id: Int,
    val video: Boolean,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String
)

/* Response */
// >> List of movie
data class MovieListResponse (
    val page: Int?,
    val results: List<MovieItem>?,
    val total_pages: Int?,
    val total_results: Int?,
    val dates: DateRange
)

data class DateRange (
    val maximum: String,
    val minimum: String
)

// >> Detail of movie
data class MovieDetailResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Long,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val status: String,
    val title: String,
    val revenue: Long,
    val vote_average: Double,
    val vote_count: Int,
    val runtime: Int,
    val spoken_languages: List<Languages>
)

data class Languages(
    val iso_639_1: String,
    val name: String
)
