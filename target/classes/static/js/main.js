
function getIndex(list, id) {
    for(var i = 0; i < list.length; i++){
        if(list[i].id === id){
            return i;
        }
    }
    return -1;
}


var paymentApi = Vue.resource('payment{/id}');

Vue.component('payment-form',{
    props: ['payments', 'paymentAttr'],
    data: function () {
        return{
            cash:'',
            name:'',
            id:''
        }
    },
    watch: {
        paymentAttr: function(newVal, oldVal){
            this.cash = newVal.cash,
                this.name = newVal.name,
                this.id = newVal.id;
        }
    },
    template:
        '<div>'+
        '<input type="text" placeholder="Введите имя" v-model="name"/>'+
        '<input type="text" placeholder="Введите платеж" v-model="cash"/>'+
        '<input type="button" value="Сохранить" v-on:click="save"/>'+
        '</div>',
    methods:{
        save: function () {
            var cash = this.cash;
            var name = this.name;

            if(this.id){
                paymentApi.update({id:this.id}, {cash, name}).then(result =>
                    result.json().then(data =>{
                        var index = getIndex(this.payments, data.id)
                        this.payments.splice(index, 1, data);
                        this.cash = '';
                        this.name = '';
                        this.id = ''
                    })
                )
            } else{
                paymentApi.save(cash,name).then(result =>
                    result.json().then(data => {
                        this.payments.push(data);
                        this.cash = '';
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('payment-row',{
    props: ['payment','editMethod', 'payments'],
    template: '<div>'+
        '<b>{{payment.id}}  </b>-  {{payment.name}}  -<i>  {{payment.cash}}</i>'+
        '<span style="position: absolute; right: 0">'+
        '<input type="button" value="Редактировать" @click="edit" />'+
        '<input type="button" value="X" @click="del" />'+
        '</span>'+
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.payment);
        },
        del: function () {
            paymentApi.remove({id: this.payment.id}).then(result => {
                if(result.ok){
                    this.payment.splice(this.payments.indexOf(this.payment),1)
                }
            })
        }
    }
});

Vue.component('payments-list',{
    props: ['payments'],
    data: function(){
        return {
            payment: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<payment-form :payments="payments" :paymentAttr="payment"/>'+'<br>'+
        '<payment-row v-for="payment in payments" :key="payment.id" :payment="payment" :editMethod="editMethod" '+
        ':payments="payments"/>'+
        '</div>',
    created: function () {
        paymentApi.get().then(result =>
            result.json().then(data =>
                data.forEach(payment => this.payments.push(payment))
            )
        )
    },
    methods:{
        editMethod: function (payment) {
            this.payment = payment;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<payments-list :payments="payments"/>',
    data:{
        payments: []
    }
});