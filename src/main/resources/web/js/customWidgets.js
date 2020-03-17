zk.afterLoad("zul.inp", function () {
	zul.inp.CustomEnterTextbox = zk.$extends(zul.inp.Textbox, {
	    doKeyDown_: function (event) {
			if(event.domEvent.key == "Enter" && this._next != null){
				zk.$(this._next).focus();
			}else{
		      	this.$supers("doKeyDown_", arguments);
			}
	    }
	  });
	zul.inp.CustomCtrlShiftFunctionKeyTextbox = zk.$extends(zul.inp.Textbox, {
	    doKeyDown_: function (event) {
			if(event.domEvent.key == "F1" && event.domEvent.shiftKey && event.domEvent.ctrlKey){
				this.fire("onCustomKeyEvent",null,{toServer:true});
				event.stop();
			}else{
				//do default
				this.$supers('doKeyDown_', arguments);
			}
	    }
	  });
	zul.inp.CustomBankEntryFormTextbox = zk.$extends(zul.inp.Textbox, {
	    doKeyDown_: function (event) {
			 if(this._next != null && (event.domEvent.key == "Enter" || (event.domEvent.key == "Tab" && ! event.shiftKey))){
		 		zk.$(this._next).focus();
				event.stop();
				return;
			}
			// shift+tab keys
			if( this._previous != null && event.domEvent.key == "Tab" && event.domEvent.shiftKey){
				zk.$(this._previous).focus();
				event.stop();
				return;
			}
			//input full
			if(this._next != null && this.$n().value.length >= this._maxlength){
				zk.$(this._next).focus();
				return;
			}
			//input empty and backspace key
			if(this._previous != null && this.$n().value.length == 0 && event.domEvent.key == "Backspace"){
				zk.$(this._previous).focus();
				return;
			}
			//do default
		  	this.$supers('doKeyDown_', arguments);
		}
	});
});