export const downloadFile = (blobData, fileName) => {
    let url = window.URL.createObjectURL(blobData);
    let a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    a.click();
    window.URL.revokeObjectURL(url);
}