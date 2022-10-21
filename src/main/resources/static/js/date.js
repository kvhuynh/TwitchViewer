const start = $('#start_date').val();
const currentDate = new Date();
const currentMonth = d.getMonth() + 1;
const currentDay = d.getDate();

const output = d.getFullYear() + '/' +
    ((''+month).length<2 ? '0' : '') + month + '/' +
    ((''+day).length<2 ? '0' : '') + day;

// end - start returns difference in milliseconds 
const diff = new Date(end - start);

// get days
const days = diff/1000/60/60/24;