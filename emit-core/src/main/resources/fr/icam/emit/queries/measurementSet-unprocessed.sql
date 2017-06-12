select mea.*,ins.*,fea.* 
from emit.measurementsets mea
inner join emit.instruments ins
on mea.instrument = ins.uri
inner join (select id as id_feature,measure,instrument as instrument_f, no_order as no_order from emit.features)fea
on fea.instrument_f = ins.uri
where id = (select id from emit.measurementsets where achieved is null and data is not null order by id limit 1)
order by no_order;