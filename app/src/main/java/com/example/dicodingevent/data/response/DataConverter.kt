package com.example.dicodingevent.data.response

import com.example.dicodingevent.data.database.FavoriteEntity

fun convertEventItemToFavoriteEvent(input: EventsItem) : FavoriteEntity {
    return FavoriteEntity(
        summary = input.summary,
        mediaCover = input.mediaCover,
        registrants = input.registrants,
        imageLogo = input.imageLogo,
        link = input.link,
        description = input.description,
        ownerName = input.ownerName,
        cityName = input.cityName,
        quota = input.quota,
        name = input.name,
        id = input.id,
        beginTime = input.beginTime,
        endTime = input.endTime,
        category = input.category
    )
}

fun convertFavoriteEventToEventItem(input: FavoriteEntity) : EventsItem {
    return EventsItem(
        summary = input.summary,
        mediaCover = input.mediaCover,
        registrants = input.registrants,
        imageLogo = input.imageLogo,
        link = input.link,
        description = input.description,
        ownerName = input.ownerName,
        cityName = input.cityName,
        quota = input.quota,
        name = input.name,
        id = input.id,
        beginTime = input.beginTime,
        endTime = input.endTime,
        category = input.category
    )
}