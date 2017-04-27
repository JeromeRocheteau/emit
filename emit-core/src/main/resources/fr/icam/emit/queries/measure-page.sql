select name, unit
from measures
where deleted = 0
limit ?,5;