var emit = {};

var invalid = function(value) {
	return value == null || value == undefined || value === "";
}

emit.user = {};
	    
emit.user.name = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/user/name', true);
  req.send(null);    
};
	    
emit.user.role = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/user/role', true);
  req.send(null);    
};
	    
emit.user.register = function(data, onSuccess, onError) {
  var parameters = "?username=" + encodeURIComponent(data.username) 
  + "&rolename=" + encodeURIComponent(data.rolename) 
  + "&password=" + encodeURIComponent(data.password) 
  + "&confirmPassword=" + encodeURIComponent(data.confirmPassword);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/user/register' + parameters, true);
  req.send(null);    
};
	    
emit.user.update = function(data, onSuccess, onError) {
  var parameters = "?username=" + encodeURIComponent(data.username) 
  + "&rolename=" + encodeURIComponent(data.rolename) 
  + "&password=" + encodeURIComponent(data.password) 
  + "&confirmPassword=" + encodeURIComponent(data.confirmPassword);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/user/update' + parameters, true);
  req.send(null);    
};

emit.brokers= {};

emit.brokers.size = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/size', true);
  req.send(null);    
};

emit.brokers.page = function(data, onSuccess, onError) {
  var parameters = "?offset=" + data.offset + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/page' + parameters, true);
  req.send(null);    
};

emit.brokers.list = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/list', true);
  req.send(null);    
};
	    
emit.brokers.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&uri=" + encodeURIComponent(data.uri) 
  + (invalid(data.username) ? "" : "&username=" + encodeURIComponent(data.username)) 
  + (invalid(data.password) ? "" : "&password=" + encodeURIComponent(data.password));
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/create' + parameters, true);
  req.send(null);    
};
	    
emit.brokers.update = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&uri=" + encodeURIComponent(data.uri) 
  + (invalid(data.username) ? "" : "&username=" + encodeURIComponent(data.username)) 
  + (invalid(data.password) ? "" : "&password=" + encodeURIComponent(data.password));
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/update' + parameters, true);
  req.send(null);    
};
	    
emit.brokers.delete = function(data, onSuccess, onError) {
  var parameters = "?uri=" + encodeURIComponent(data.uri);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/delete' + parameters, true);
  req.send(null);    
};

emit.clients = {};

emit.clients.size = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/clients/size', true);
  req.send(null);    
};

emit.clients.page = function(data, onSuccess, onError) {
  var parameters = "?offset=" + data.offset 
  + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/clients/page' + parameters, true);
  req.send(null);    
};
	    
emit.clients.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&broker=" + encodeURIComponent(data.broker) 
  + "&open=" + data.open;;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/create' + parameters, true);
  req.send(null);    
};
	    
emit.clients.update = function(data, onSuccess, onError) {
  var parameters = "?uuid=" + data.uuid 
  + "&name=" + encodeURIComponent(data.name) 
  + "&broker=" + encodeURIComponent(data.broker) 
  + "&open=" + data.open;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/update' + parameters, true);
  req.send(null);    
};
	    
emit.clients.delete = function(data, onSuccess, onError) {
  var parameters = "?uuid=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/delete' + parameters, true);
  req.send(null);    
};

emit.clients.connected = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/clients/connected' + parameters, true);
  req.send(null);    
};
	    
emit.clients.connect = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/connect' + parameters, true);
  req.send(null);    
};
	    
emit.clients.disconnect = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&connect=" + data.connected.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/disconnect' + parameters, true);
  req.send(null);    
};

emit.clients.subscribing = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/clients/subscribing' + parameters, true);
  req.send(null);    
};
	    
emit.clients.subscribe = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.filter);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/subscribe' + parameters, true);
  req.send(null);    
};
	    
emit.clients.unsubscribe = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.subscribed.topic) 
  + "&subscribe=" + data.subscribed.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/unsubscribe' + parameters, true);
  req.send(null);    
};
	    
emit.clients.publish = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.topic) 
  + "&qos=" + data.qos 
  + "&retained=" + data.retained 
  + "&persisted=" + data.persisted 
  + "&payload=" + encodeURIComponent(data.payload);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/clients/publish' + parameters, true);
  req.send(null);    
};

emit.records = {};

emit.records.size = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/records/size' + parameters, true);
  req.send(null);    
};

emit.records.page = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&offset=" + data.offset 
  + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/records/page' + parameters, true);
  req.send(null);    
};

emit.messages = {};

emit.messages.size = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/messages/size' + parameters, true);
  req.send(null);    
};

emit.messages.page = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&offset=" + data.offset 
  + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/messages/page' + parameters, true);
  req.send(null);    
};

emit.messages.search = function(data, onSuccess, onError) {
  var parameters = "?topic=" + encodeURIComponent(data.topic) 
  + "&started=" + data.started 
  + "&stopped=" + data.stopped;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/messages/search' + parameters, true);
  req.send(null);    
};

emit.types = {};

emit.types.list = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/types/list', true);
  req.send(null);    
};

emit.symbols = {};

emit.symbols.list = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/symbols/list', true);
  req.send(null);    
};

emit.callbacks = {};

emit.callbacks.size = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/size', true);
  req.send(null);    
};

emit.callbacks.page = function(data, onSuccess, onError) {
  var parameters = "?offset=" + data.offset 
  + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/page' + parameters, true);
  req.send(null);    
};

emit.callbacks.list = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/list', true);
  req.send(null);    
};
	    
emit.callbacks.delete = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/delete' + parameters, true);
  req.send(null);    
};

emit.callbacks.types = {};

emit.callbacks.types.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&type=" + encodeURIComponent(data.type);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/create/type' + parameters, true);
  req.send(null);    
};

emit.callbacks.types.update = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id
  + "&name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&type=" + encodeURIComponent(data.type);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/update/type' + parameters, true);
  req.send(null);    
};

emit.callbacks.types.find = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/find/type' + parameters, true);
  req.send(null);    
};

emit.callbacks.topics = {};

emit.callbacks.topics.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&topic=" + encodeURIComponent(data.topic);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/create/topic' + parameters, true);
  req.send(null);    
};

emit.callbacks.topics.update = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id
  + "&name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&topic=" + encodeURIComponent(data.topic);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/update/topic' + parameters, true);
  req.send(null);    
};

emit.callbacks.topics.find = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/find/topic' + parameters, true);
  req.send(null);    
};

emit.callbacks.storages = {};

emit.callbacks.storages.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&collection=" + encodeURIComponent(data.collection);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/create/storage' + parameters, true);
  req.send(null);    
};

emit.callbacks.storages.update = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id
  + "&name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&collection=" + encodeURIComponent(data.collection);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/update/storage' + parameters, true);
  req.send(null);    
};

emit.callbacks.storages.find = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/find/storage' + parameters, true);
  req.send(null);    
};

emit.callbacks.features = {};

emit.callbacks.features.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&symbol=" + data.symbol
  + "&type=" + data.type
  + "&value=" + encodeURIComponent(data.value);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/create/feature' + parameters, true);
  req.send(null);    
};

emit.callbacks.features.update = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id
  + "&name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&symbol=" + data.symbol
  + "&type=" + data.type
  + "&value=" + encodeURIComponent(data.value);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/update/feature' + parameters, true);
  req.send(null);    
};

emit.callbacks.features.find = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/find/feature' + parameters, true);
  req.send(null);    
};

emit.callbacks.guards = {};

emit.callbacks.guards.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&test=" + data.test
  + "&success=" + data.success
  + (invalid(data.failure) ? "" : "&failure=" + data.failure);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/create/guard' + parameters, true);
  req.send(null);    
};

emit.callbacks.guards.update = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id
  + "&name=" + encodeURIComponent(data.name) 
  + "&atomic=" + data.atomic 
  + "&category=" + encodeURIComponent(data.category)
  + "&test=" + data.test
  + "&success=" + data.success
  + (invalid(data.failure) ? "" : "&failure=" + data.failure);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/callbacks/update/guard' + parameters, true);
  req.send(null);    
};

emit.callbacks.guards.find = function(data, onSuccess, onError) {
  var parameters = "?id=" + data.id;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/callbacks/find/guard' + parameters, true);
  req.send(null);    
};

