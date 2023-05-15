export default function getObjectHeaders(
    Obj,
    columnsWidth,
    editable = null,
    widthComponents = null,
    renderCells = null,
    customColumns = null) {
    if (columnsWidth === null) {
        return
    }
    if (Obj == null) {
        return
    }
    const keys = Object.keys({...Obj, ...customColumns});
    var columns = [];
    keys.forEach(key => {
        if (customColumns != null && key in customColumns) {
            customColumns[key].field = key;
            columns.push(customColumns[key])
        } else {
            var colWithd = columnsWidth
            var columnDefinition = {
                field: key,
                headerName: key,
                width: colWithd,
            }
            if (widthComponents != null && key in widthComponents) {
                columnDefinition.width = widthComponents[key]
            }
            if (renderCells != null && renderCells[key] !== undefined) {
                columnDefinition.renderCell = renderCells[key];
            }
            if (!String(key).includes("id")) {
                columnDefinition.editable = editable[key] ?? false;
            }
            else {
                columnDefinition.editable = false;
            };
            columns.push(columnDefinition)
        }

    });
    return columns
}

export const getColumnByColumnName = (data, fieldName) => {
    const result = []
    data?.forEach((object) => {
        if (fieldName in object) {
            result.push(object[fieldName])
        }
    })
    return result;

}

export const getFieldIdFromData= (data, fieldName, fieldValue) => {
    let resultId;
    data?.forEach((object) => {
        if (fieldName in object && object[fieldName] === fieldValue) {
            resultId = object.id;
        }
    })
    return resultId;
}