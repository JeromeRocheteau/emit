select e.*,m.*,ev.* 
from emit.experiments e
INNER JOIN emit.environments ev
on e.environment = ev.uri
INNER JOIN (select id as exp_set_id,data,achieved,experiment,instrument from emit.measurementsets)m
on e.id = m.experiment
where id =  (select id from emit.experiments where stopped is null order by id desc limit 1)
order by id desc;