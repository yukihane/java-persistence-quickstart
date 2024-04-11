select
    /*%expand*/*
from
    employee
where
    id in /* ids */(1, 2)
