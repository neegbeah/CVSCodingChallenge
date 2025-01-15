package com.nr.cvsflickrcodechallenge.data


/**
 * Represents the overall response from Flickr API's public photos feed. Note using Gson Converter Factory.
 * Assuming it already Serialized without annotations
 *
 * @property title The title of the feed.
 * @property link The URL of the feed.
 * @property description The description of the feed.
 * @property modified The date/time the feed was last modified.
 * @property generator The generator of the feed (typically the Flickr website).
 * @property items A list of [Photo] objects, each representing a photo in the feed.
 *
 * @author Neegbeah Reeves
 */
data class PhotoResponse(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String, val items: List<Photo>
)

/**
 * Represents a single photo item in the public photos feed.
 *
 * @property title The title of the photo.
 * @property link The URL of the photo's page on Flickr.
 * @property media A [Media] object containing URLs to different sizes of the photo.
 * @property dateTaken The date and time the photo was taken.
 * @property description The description of the photo (may contain HTML).
 * @property published The date and time the photo was published to the feed.
 * @property author The author of the photo (in the format "nobody@flickr.com ("author_name")").
 * @property authorId The Flickr ID of the author.
 * @property tags A space-separated list of tags associated with the photo.
 */
data class Photo(
    val title: String,
    val link: String,
    val media: Media,
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    val authorId: String,
    val tags: String
)

/**
 * Represents the media information, specifically the URL to the medium-sized version.
 *
 * @property m The URL of the medium-sized version of the photo.
 */
data class Media(val m: String?) // Where the actual source JPG will be at.