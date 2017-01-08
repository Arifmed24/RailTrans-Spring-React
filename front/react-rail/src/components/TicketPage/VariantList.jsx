import React, { PropTypes, Component } from 'react';
import Variant from './Variant';



class VariantList extends Component {

	componentWillMount(){
		this.props.variants.tickets = [];
	}
	render() {
		const { tickets } = this.props.variants;
		return (
			<div>
				<h3>Variants</h3>
				{
					tickets.map(function(ticket,index){
						return (
								<Variant index={index} ticket={ticket}/>
						)
					})
				}
			</div>
		)
	}
}

export default VariantList;
