export const isRangeIn = (intervalsArray, dateFrom, dateTo) => {
    let result = false;
    for (let i = 0; i < intervalsArray.length && !result; i++) {
        let _dateFrom = intervalsArray[i].dateFrom;
        let _dateTo = intervalsArray[i].dateTo;
        if ((_dateTo >= dateFrom && _dateTo <= dateTo)
            || (_dateFrom >= dateFrom && _dateFrom <= dateTo)) {
            result = true;
        }
    }
}

export const isInAnyInterval = (intervalsArray, dateTime) => {
    let result = false;
    for (let i = 0; i < intervalsArray.length && !result; i++) {
        let dateFrom = intervalsArray[i].dateFrom;
        let dateTo = intervalsArray[i].dateTo;
        if (dateTime >= dateFrom && dateTime <= dateTo) {
            result = true;
        }
    }
    return result
}