export default {
	async postItems(itemDto) {
		try {
			const response = await $.ajax({
				url: 'http://localhost:8080/api/v1/item',
				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(itemDto)
			});
			console.log('Item posted successfully:', response);
		} catch (error) {
			console.error('Error posting item:', error);
		}
	},
	
	async getItems() {
		var items = [];
		try {
				const data = await $.ajax({
					url: 'http://localhost:8080/api/v1/item',
					method: 'GET'
				});
				for (const item of data) {
					items.push(item);
				}
				return items;
		} catch (error) {
			console.error('Error fetching items:', error);
		}
	},

	async getItem(id) {
		try {
				return await $.ajax({
					url: `http://localhost:8080/api/v1/item/${id}`,
					method: 'GET'
				});
		} catch (error) {
			console.error('Error fetching items:', error);
		}
	},

	async postText(textDto, itemId) {
		try {
			const response = await $.ajax({
				url: `http://localhost:8080/api/v1/text/${itemId}`,
				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(textDto)
			});
			console.log('Text posted successfully:', response);
		} catch (error) {
			console.error('Error posting text:', error);
		}
	}
}