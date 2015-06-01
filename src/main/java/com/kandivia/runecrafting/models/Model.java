package com.kandivia.runecrafting.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class Model extends ModelBiped
{
  //fields
    ModelRenderer head;
  
  public Model(float expand)
  {
    super(expand, 0, 64, 64);
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-4F, -16F, -4F, 8, 8, 8);
    head.setRotationPoint(0F, 0F, 0F);
    head.setTextureSize(64, 64);
    head.mirror = false;
    setRotation(head, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
  super.render(entity, f, f1, f2, f3, f4, f5);
  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  head.render(f5);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
  model.rotateAngleX = x;
  model.rotateAngleY = y;
  model.rotateAngleZ = z;
}


}
