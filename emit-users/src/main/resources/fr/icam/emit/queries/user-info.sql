select u.username as username, u.firstname as firstname, u.lastname as lastname, u.type as type
from cookie c 
inner join user u on c.username = u.username
where passphrase = ?;