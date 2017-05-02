select mea.*,ins.*,fea.* 
from emit.measurementsets mea
inner join emit.instruments ins
on mea.instrument = ins.uri
inner join (select id as id_feature,measure,instrument as instrument_f,unit from emit.features)fea
on fea.instrument_f = ins.uri
where id = (select id from emit.measurementsets where achieved is null order by id desc limit 1)
order by id desc;