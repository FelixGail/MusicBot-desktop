@file:JvmName("Util")

package com.github.bjoernpetersen.deskbot.api.swag.api.impl

import com.github.bjoernpetersen.jmusicbot.ProviderManager
import com.github.bjoernpetersen.jmusicbot.Song
import com.github.bjoernpetersen.jmusicbot.playback.PlayerState
import com.github.bjoernpetersen.jmusicbot.playback.QueueEntry
import com.github.bjoernpetersen.jmusicbot.playback.SongEntry
import com.github.bjoernpetersen.jmusicbot.provider.NoSuchSongException
import com.github.bjoernpetersen.jmusicbot.provider.Provider
import com.github.bjoernpetersen.jmusicbot.provider.Suggester
import java.util.*

private typealias ModelSong = com.github.bjoernpetersen.deskbot.api.swag.model.Song
private typealias ModelQueueEntry = com.github.bjoernpetersen.deskbot.api.swag.model.QueueEntry
private typealias ModelSongEntry = com.github.bjoernpetersen.deskbot.api.swag.model.SongEntry
private typealias ModelPlayerState = com.github.bjoernpetersen.deskbot.api.swag.model.PlayerState
private typealias ModelPlayerStateEnum = com.github.bjoernpetersen.deskbot.api.swag.model.PlayerState.StateEnum

fun Song.convert(): ModelSong {
    val result = ModelSong()
    result.id = this.id
    result.providerId = this.providerName
    result.title = this.title
    result.description = this.description
    result.albumArtUrl = this.albumArtUrl.orElse(null)
    return result
}

fun QueueEntry.convert(): ModelQueueEntry {
    val queueEntry = ModelQueueEntry()
    queueEntry.userName = this.user.name
    queueEntry.song = this.song.convert()
    return queueEntry
}

fun SongEntry.convert(): ModelSongEntry {
    val song = this.song.convert()
    val user = this.user
    val userName = user?.name
    val queueEntry = ModelSongEntry()
    queueEntry.userName = userName
    queueEntry.song = song
    return queueEntry
}

fun List<QueueEntry>.convert(): List<ModelQueueEntry> = this.map { it.convert() }

fun PlayerState.convert(): ModelPlayerState {
    val result = ModelPlayerState()
    result.state = ModelPlayerStateEnum.valueOf(this.state.name)
    result.songEntry = this.entry
            .map { it.convert() }
            .orElse(null)
    return result
}

fun lookupProvider(manager: ProviderManager, providerId: String): Optional<Provider> =
        try {
            Optional.of(manager.getProvider(providerId))
        } catch (e: IllegalArgumentException) {
            Optional.empty()
        }

fun lookupSuggester(manager: ProviderManager, suggesterId: String): Optional<Suggester> =
        try {
            Optional.of(manager.getSuggester(suggesterId))
        } catch (e: IllegalArgumentException) {
            Optional.empty()
        }

fun lookupSong(manager: ProviderManager, songId: String, providerId: String): Optional<Song> =
        lookupProvider(manager, providerId)
                .map { provider ->
                    try {
                        provider.lookup(songId)
                    } catch (e: NoSuchSongException) {
                        null
                    }
                }