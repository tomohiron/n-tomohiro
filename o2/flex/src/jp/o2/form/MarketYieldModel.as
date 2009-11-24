package jp.o2.form
{
    import mx.collections.ArrayCollection;

    [Bindable]
    public class MarketYieldModel
    {
        public var yieldList:ArrayCollection=new ArrayCollection([{label: "JPY", data: "JPY"}, {label: "USD", data: "USD"}]);

        public function MarketYieldModel()
        {
        }
    }
}