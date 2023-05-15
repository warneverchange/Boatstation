export const filterByParams = (dataArray, ...params) => {
    const result = []

    

    for (let i = 0; i < dataArray.length; i++) {
        let isMatch = true;
        for (let j = 0; j < params.length && isMatch; j++) {
            if (params[j].value !== '' && dataArray[i][params[j].name] !== params[j].value) {
                isMatch = false;
            }
        }
        if (isMatch) {
            result.push(dataArray[i])
        }
    }
    return result;
}